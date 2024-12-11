package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOBasic;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuItemService {
    List<MenuItemDTOBasic> getAll();
    Result<Void> put(UUID uuid, MenuItemDTOExtended dtoExtended);
    Result<Void> delete(UUID uuid);
    Result<MenuItemDTOExtended> getByUuid(UUID uuid);
}
