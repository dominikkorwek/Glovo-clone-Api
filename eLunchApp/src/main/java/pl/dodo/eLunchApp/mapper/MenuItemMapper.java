package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOBasic;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOExtended;
import pl.dodo.eLunchApp.model.MenuItem;

@Mapper(componentModel = "Spring", uses = {DishMapper.class, RestaurantMapper.class})
public interface MenuItemMapper {

    MenuItemDTOBasic mapToDtoBasic(MenuItem menuItem);

    MenuItemDTOExtended mapToDtoExtended(MenuItem menuItem);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    MenuItem mapToEntity(MenuItemDTOExtended dtoExtended);
}
