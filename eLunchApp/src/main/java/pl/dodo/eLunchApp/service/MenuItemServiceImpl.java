package pl.dodo.eLunchApp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOBasic;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.mapper.MenuItemMapper;
import pl.dodo.eLunchApp.model.MenuItem;
import pl.dodo.eLunchApp.repository.MenuItemRepository;

import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "menuItems")
@RequiredArgsConstructor
public class MenuItemServiceImpl extends BaseService implements MenuItemService{
    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    @Override
    public List<MenuItemDTOBasic> getAll() {
        return menuItemRepository.findAll().stream()
                .map(menuItemMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    @CacheEvict(cacheNames = "menuItems", allEntries = true)
    public Result<Void> put(UUID uuid, MenuItemDTOExtended basicDelivererDto) {
        //todo
    }

    @Override
    @CacheEvict(cacheNames = "menuItems", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,menuItemRepository);
    }

    @Override
    public Result<MenuItemDTOExtended> getByUuid(UUID uuid) {
        return getByUuid(uuid,menuItemRepository,menuItemMapper::mapToDtoExtended, MenuItem.class);
    }
}
