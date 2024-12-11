package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.DeliveryAddress.DeliveryAddressDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.mapper.DeliveryAddressMapper;
import pl.dodo.eLunchApp.model.DeliveryAddress;
import pl.dodo.eLunchApp.repository.DeliveryAddressRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "deliveryAddreses")
public class DeliveryAdressServiceImpl extends BaseService implements DeliveryAdressService{

    private final DeliveryAddressRepository deliveryAddressRepository;
    private final DeliveryAddressMapper deliveryAddressMapper;

    @Override
    @Cacheable(cacheNames = "deliveryAddresses")
    public List<DeliveryAddressDTOExtended> getAll() {
        return deliveryAddressRepository.findAll().stream()
                .map(deliveryAddressMapper::mapToDtoExtended)
                .toList();
    }

    @Override
    @CacheEvict(cacheNames = "deliveryAddresses", allEntries = true)
    public Result<Void> put(UUID uuid, DeliveryAddressDTOExtended basicDelivererDto) {
        //todo
    }

    @Override
    @CacheEvict(cacheNames = "deliveryAddresses", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid, deliveryAddressRepository);
    }

    @Override
    public Result<DeliveryAddressDTOExtended> getByUuid(UUID uuid) {
        return getByUuid(uuid, deliveryAddressRepository,deliveryAddressMapper::mapToDtoExtended, DeliveryAddress.class);
    }
}
