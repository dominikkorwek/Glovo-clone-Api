package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOBasic;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.MenuItem;

import java.util.List;
import java.util.UUID;

public interface MenuItemService extends ValidationService<MenuItem> {
    List<MenuItemDTOBasic> getAll();
    void add(MenuItemDTOExtended dtoExtended);
    void edit(UUID uuid, MenuItemDTOExtended dtoExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound;
    void delete(UUID uuid) throws eLunchError.ObjectNotFound;
    MenuItemDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;
}
