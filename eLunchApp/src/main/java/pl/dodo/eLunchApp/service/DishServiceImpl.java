package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Dish.DishDTOExtendedd;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.DishMapper;
import pl.dodo.eLunchApp.model.Dish;
import pl.dodo.eLunchApp.model.MenuItem;
import pl.dodo.eLunchApp.model.Product;
import pl.dodo.eLunchApp.repository.DishRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "dishes")
public class DishServiceImpl extends BaseService implements DishService{

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final ProductService productService;
    private final MenuItemService menuItemService;

    @Override
    @Cacheable(cacheNames = "dishes")
    public List<DishDTOExtendedd> getAll() {
        return getAllEntites(dishRepository, dishMapper::mapToDtoExtended);
    }

    @Override
    @CacheEvict(cacheNames = "dishes", allEntries = true)
    public Result<Void> put(UUID uuid, DishDTOExtendedd dtoExtended) {
        UUID dtoUuid = dtoExtended.getDishDTOId().getUuid();
        if (!dtoUuid.equals(uuid))
            return Result.failure(new eLunchError.InvalidUuid(dtoUuid,uuid));

        Optional<Dish> byUuid = dishRepository.findByUuid(uuid);
        if (byUuid.isEmpty())
            return Result.failure(new eLunchError.ObjectNotFound(Dish.class));

        Dish dishNew = byUuid.get();
        Result<Product> productResult = validateObject(dishNew.getProduct(), productService);
        if (!productResult.isSuccess())
            return Result.failure(productResult.getError());
        Result<List<MenuItem>> listResult = validateList(dishNew.getMenuItems(), MenuItem.class, menuItemService);
        if (!listResult.isSuccess())
            return Result.failure(listResult.getError());

        dishNew.setProduct(productResult.getData());
        dishNew.setMenuItems(listResult.getData());
        byUuid.get().edit(dishNew);
        return Result.success(null);
    }

    @Override
    @CacheEvict(cacheNames = "dishes", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,dishRepository);
    }

    @Override
    public Result<DishDTOExtendedd> getByUuid(UUID uuid) {
        return getEntityByUuid(uuid,dishRepository,dishMapper::mapToDtoExtended, Dish.class);
    }
}
