package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOBasic;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.model.MenuItem;

import java.util.List;
import java.util.UUID;

public interface MenuItemService extends ValidationService<MenuItem> {
    List<MenuItemDTOBasic> getAll();
    Result<Void> put(UUID uuid, MenuItemDTOExtended dtoExtended);
    Result<Void> delete(UUID uuid);
    Result<MenuItemDTOExtended> getByUuid(UUID uuid);
}
