package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Dish.DishDTOExtendedd;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.mapper.DishMapper;
import pl.dodo.eLunchApp.model.Dish;
import pl.dodo.eLunchApp.repository.DishRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "dishes")
public class DishServiceImpl extends BaseService implements DishService{

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    @Override
    @Cacheable(cacheNames = "dishes")
    public List<DishDTOExtendedd> getAll() {
        return dishRepository.findAll().stream()
                .map(dishMapper::mapToDtoExtended)
                .toList();
    }

    @Override
    @CacheEvict(cacheNames = "dishes", allEntries = true)
    public Result<Void> put(UUID uuid, DishDTOExtendedd basicDelivererDto) {
        //todo
    }

    @Override
    @CacheEvict(cacheNames = "dishes", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,dishRepository);
    }

    @Override
    public Result<DishDTOExtendedd> getByUuid(UUID uuid) {
        return getByUuid(uuid,dishRepository,dishMapper::mapToDtoExtended, Dish.class);
    }
}
