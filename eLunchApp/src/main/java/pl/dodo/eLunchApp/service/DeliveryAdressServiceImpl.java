package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.DeliveryAddress.DeliveryAddressDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.DeliveryAddressMapper;
import pl.dodo.eLunchApp.model.DeliveryAddress;
import pl.dodo.eLunchApp.model.User;
import pl.dodo.eLunchApp.repository.DeliveryAddressRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "deliveryAddreses")
public class DeliveryAdressServiceImpl extends BaseService implements DeliveryAdressService{

    private final DeliveryAddressRepository deliveryAddressRepository;
    private final DeliveryAddressMapper deliveryAddressMapper;
    private final UserService userService;

    @Override
    @Cacheable(cacheNames = "deliveryAddresses")
    public List<DeliveryAddressDTOExtended> getAll() {
        return getAllEntites(deliveryAddressRepository,deliveryAddressMapper::mapToDtoExtended);
    }

    @Override
    public Result<Void> add(DeliveryAddressDTOExtended dtoExtended) {
        return addEntity(dtoExtended, deliveryAddressRepository, deliveryAddressMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "deliveryAddresses", allEntries = true)
    public Result<Void> edit(UUID uuid, DeliveryAddressDTOExtended dtoExtended) {
        if (!dtoExtended.getUuid().equals(uuid))
            return Result.failure(new eLunchError.InvalidUuid(dtoExtended.getUuid(),uuid));

        Optional<DeliveryAddress> byUuid = deliveryAddressRepository.findByUuid(uuid);
        if (byUuid.isEmpty())
            return Result.failure(new eLunchError.ObjectNotFound(DeliveryAddress.class));

        DeliveryAddress addressNew = deliveryAddressMapper.mapToEntity(dtoExtended);
        Result<User> userResult = validateObject(addressNew.getUser(), userService);

        if (!userResult.isSuccess())
            return Result.failure(userResult.getError());

        addressNew.setUser(userResult.getData());
        byUuid.get().edit(addressNew);
        return Result.success(null);
    }

    @Override
    @CacheEvict(cacheNames = "deliveryAddresses", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid, deliveryAddressRepository);
    }

    @Override
    public Result<DeliveryAddressDTOExtended> getByUuid(UUID uuid) {
        return getEntityByUuid(uuid, deliveryAddressRepository,deliveryAddressMapper::mapToDtoExtended, DeliveryAddress.class);
    }
}
