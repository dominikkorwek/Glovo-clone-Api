package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOBasic;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.mapper.RestaurantMapper;
import pl.dodo.eLunchApp.model.Restaurant;
import pl.dodo.eLunchApp.repository.RestaurantRepository;

import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "restaurants")
@RequiredArgsConstructor
public class RestaurantServiceImpl extends BaseService implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public List<RestaurantDTOBasic> getAll() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    @CacheEvict(cacheNames = "restaurants", allEntries = true)
    public void put(UUID uuid, RestaurantDTOExtended restaurantDTOExtended) {
        //todo
    }

    @Override
    @CacheEvict(cacheNames = "restaurants", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,restaurantRepository);
    }

    @Override
    public Result<RestaurantDTOExtended> getByUuid(UUID uuid) {
        return getEntityByUuid(uuid,restaurantRepository,restaurantMapper::mapToDtoExtended, Restaurant.class);
    }
}