package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.DeliveryAddress.DeliveryAddressDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.DeliveryAddressMapper;
import pl.dodo.eLunchApp.model.DeliveryAddress;
import pl.dodo.eLunchApp.model.User;
import pl.dodo.eLunchApp.repository.DeliveryAddressRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "deliveryAddreses")
public class DeliveryAddressServiceImpl extends BaseService implements DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;
    private final DeliveryAddressMapper deliveryAddressMapper;
    private final UserService userService;

    @Override
    @Cacheable(cacheNames = "deliveryAddresses")
    public List<DeliveryAddressDTOExtended> getAll() {
        return getAllEntites(deliveryAddressRepository,deliveryAddressMapper::mapToDtoExtended);
    }

    @Override
    public void add(DeliveryAddressDTOExtended dtoExtended) {
        addEntity(dtoExtended, deliveryAddressRepository, deliveryAddressMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "deliveryAddresses", allEntries = true)
    public void edit(UUID uuid, DeliveryAddressDTOExtended dtoExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound {
        if (!dtoExtended.getUuid().equals(uuid))
            throw new eLunchError.InvalidUuid(dtoExtended.getUuid(),uuid);

        DeliveryAddress byUuid = deliveryAddressRepository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.ObjectNotFound(DeliveryAddress.class));

        User userResult = validateObject(byUuid.getUser(), userService);
        byUuid.setUser(userResult);

        byUuid.edit(byUuid);
    }

    @Override
    @CacheEvict(cacheNames = "deliveryAddresses", key = "#uuid")
    public void delete(UUID uuid) throws eLunchError.ObjectNotFound {
        deleteEntity(uuid, deliveryAddressRepository);
    }

    @Override
    public DeliveryAddressDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid, deliveryAddressRepository,deliveryAddressMapper::mapToDtoExtended, DeliveryAddress.class);
    }

    @Override
    public DeliveryAddress validate(DeliveryAddress object) throws eLunchError.ObjectNotFound {
        return getEntityByUuid(deliveryAddressRepository, object.getUuid(), DeliveryAddress.class);
    }
}
