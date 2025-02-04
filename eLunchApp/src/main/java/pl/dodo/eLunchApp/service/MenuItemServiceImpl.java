package pl.dodo.eLunchApp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOBasic;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.MenuItemMapper;
import pl.dodo.eLunchApp.model.Dish;
import pl.dodo.eLunchApp.model.MenuItem;
import pl.dodo.eLunchApp.model.Restaurant;
import pl.dodo.eLunchApp.repository.MenuItemRepository;

import java.util.List;
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
    public void add(MenuItemDTOExtended dtoExtended) {
        addEntity(dtoExtended, menuItemRepository, menuItemMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "menuItems", allEntries = true)
    public void edit(UUID uuid, MenuItemDTOExtended dtoExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound {
        UUID dtoUuid = dtoExtended.getMenuItemDTOBasic().getUuid();
        if (!dtoUuid.equals(uuid))
            throw new eLunchError.InvalidUuid(dtoUuid, uuid);

        MenuItem byUuid = menuItemRepository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.ObjectNotFound(MenuItem.class));
        MenuItem menuItemNew = menuItemMapper.mapToEntity(dtoExtended);

        List<Dish> listResult = validateList(menuItemNew.getDishes(), dishService);
        Restaurant restaurantResult = validateObject(menuItemNew.getRestaurant(), restaurantService);

        menuItemNew.setRestaurant(restaurantResult);
        menuItemNew.setDishes(listResult);

        byUuid.edit(menuItemNew);
    }

    @Override
    @CacheEvict(cacheNames = "menuItems", key = "#uuid")
    public void delete(UUID uuid) throws eLunchError.ObjectNotFound {
        deleteEntity(uuid,menuItemRepository);
    }

    @Override
    public MenuItemDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid,menuItemRepository,menuItemMapper::mapToDtoExtended, MenuItem.class);
    }

    @Override
    public MenuItem validate(MenuItem menuItem) throws eLunchError.ObjectNotFound {
        return getEntityByUuid(menuItemRepository, menuItem.getUuid(), MenuItem.class);
    }
}
