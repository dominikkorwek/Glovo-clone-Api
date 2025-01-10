package pl.dodo.eLunchApp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOBasic;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.MenuItemMapper;
import pl.dodo.eLunchApp.model.Dish;
import pl.dodo.eLunchApp.model.MenuItem;
import pl.dodo.eLunchApp.model.Restaurant;
import pl.dodo.eLunchApp.repository.MenuItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "menuItems")
@RequiredArgsConstructor
public class MenuItemServiceImpl extends BaseService implements MenuItemService{
    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;
    private final DishService dishService;
    private final RestaurantService restaurantService;

    @Override
    public List<MenuItemDTOBasic> getAll() {
        return getAllEntites(menuItemRepository,menuItemMapper::mapToDtoBasic);
    }

    @Override
    @CacheEvict(cacheNames = "menuItems", allEntries = true)
    public Result<Void> put(UUID uuid, MenuItemDTOExtended dtoExtended) {
        UUID dtoUuid = dtoExtended.getMenuItemDTOBasic().getUuid();
        if (!dtoUuid.equals(uuid))
            return Result.failure(new eLunchError.InvalidUuid(dtoUuid, uuid));

        Optional<MenuItem> byUuid = menuItemRepository.findByUuid(uuid);
        if (byUuid.isEmpty())
            return Result.failure(new eLunchError.ObjectNotFound(MenuItem.class));

        MenuItem menuItemNew = menuItemMapper.mapToEntity(dtoExtended);
        Result<List<Dish>> listResult = validateList(menuItemNew.getDishes(), Dish.class, dishService);
        if (!listResult.isSuccess())
            return Result.failure(listResult.getError());
        Result<Restaurant> restaurantResult = validateObject(menuItemNew.getRestaurant(), restaurantService);
        if (!restaurantResult.isSuccess())
            return Result.failure(restaurantResult.getError());

        MenuItem menuItem = byUuid.get();
        menuItem.setRestaurant(restaurantResult.getData());
        menuItem.setDishes(listResult.getData());
        return Result.success(null);
    }

    @Override
    @CacheEvict(cacheNames = "menuItems", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,menuItemRepository);
    }

    @Override
    public Result<MenuItemDTOExtended> getByUuid(UUID uuid) {
        return getEntityByUuid(uuid,menuItemRepository,menuItemMapper::mapToDtoExtended, MenuItem.class);
    }

    @Override
    public Result<MenuItem> validate(MenuItem user) {
        return null;
    }
}
