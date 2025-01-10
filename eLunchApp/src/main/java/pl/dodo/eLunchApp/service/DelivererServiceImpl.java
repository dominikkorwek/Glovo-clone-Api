package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOBasic;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.DelivererMapper;
import pl.dodo.eLunchApp.model.Deliverer;
import pl.dodo.eLunchApp.model.Order;
import pl.dodo.eLunchApp.repository.DelivererRepository;

import java.util.List;
import java.util.Optional;
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
    public Result<Void> add(DelivererDTOExtended dtoExtended) {
        return addEntity(dtoExtended,delivererRepository,delivererMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "deliveres", allEntries = true)
    public Result<Void> edit(UUID uuid, DelivererDTOExtended delivererDto) {
        UUID dtoUuid = delivererDto.getDelivererDTOBasic().getEmployeeDTOBasic().getEmployeeDTOId().getUuid();
        if(!dtoUuid.equals(uuid))
            return Result.failure(new eLunchError.InvalidUuid(dtoUuid, uuid));

        Optional<Deliverer> byUuid = delivererRepository.findByUuid(uuid);
        if (byUuid.isEmpty())
            return Result.failure(new eLunchError.ObjectNotFound(Deliverer.class));

        Deliverer delivererNew = delivererMapper.mapToEntity(delivererDto);
        Result<List<Order>> listResult = validateList(delivererNew.getOrders(), Order.class, orderService);

        if (!listResult.isSuccess())
            return Result.failure(listResult.getError());

        delivererNew.setOrders(listResult.getData());
        byUuid.get().edit(delivererNew);
        return Result.success(null);
    }


    @Override
    @CacheEvict(cacheNames = "deliveres", allEntries = true)
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,delivererRepository);
    }

    @Override
    public Result<DelivererDTOExtended> getByUuid(UUID uuid) {
        return getEntityByUuid(uuid,delivererRepository,delivererMapper::mapToDtoExtended,Deliverer.class);
    }
}
