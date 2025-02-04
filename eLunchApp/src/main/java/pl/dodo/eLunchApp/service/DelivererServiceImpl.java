package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOBasic;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.DelivererMapper;
import pl.dodo.eLunchApp.model.Deliverer;
import pl.dodo.eLunchApp.model.Order;
import pl.dodo.eLunchApp.repository.DelivererRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "deliveres")
public class DelivererServiceImpl extends BaseService implements DelivererService{
    private final DelivererRepository delivererRepository;
    private final DelivererMapper delivererMapper;
    private final OrderService orderService;

    @Override
    @Cacheable(cacheNames = "deliveres")
    public List<DelivererDTOBasic> getAll() {
        return getAllEntites(delivererRepository,delivererMapper::mapToDtoBasic);
    }

    @Override
    public void add(DelivererDTOExtended dtoExtended) {
        addEntity(dtoExtended,delivererRepository,delivererMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "deliveres", allEntries = true)
    public void edit(UUID uuid, DelivererDTOExtended delivererDto) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound {
        UUID dtoUuid = delivererDto.getDelivererDTOBasic()
                .getEmployeeDTOBasic()
                .getEmployeeDTOId()
                .getUuid();

        if(!dtoUuid.equals(uuid))
            throw new eLunchError.InvalidUuid(dtoUuid, uuid);

        Deliverer byUuid = delivererRepository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.ObjectNotFound(Deliverer.class));

        Deliverer delivererNew = delivererMapper.mapToEntity(delivererDto);
        List<Order> listResult = validateList(delivererNew.getOrders(), orderService);

        delivererNew.setOrders(listResult);
        byUuid.edit(delivererNew);
    }


    @Override
    @CacheEvict(cacheNames = "deliveres", allEntries = true)
    public void delete(UUID uuid) throws eLunchError.ObjectNotFound {
        deleteEntity(uuid,delivererRepository);
    }

    @Override
    public DelivererDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid,delivererRepository,delivererMapper::mapToDtoExtended,Deliverer.class);
    }

    @Override
    public Deliverer validate(Deliverer object) throws eLunchError.ObjectNotFound {
        return getEntityByUuid(delivererRepository, object.getUuid(), Deliverer.class);
    }
}
