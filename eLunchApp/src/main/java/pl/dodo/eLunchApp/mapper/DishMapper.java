package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Dish.DishDTOExtendedd;
import pl.dodo.eLunchApp.dto.Dish.DishDTOId;
import pl.dodo.eLunchApp.model.Dish;
import pl.dodo.eLunchApp.model.MenuItem;

@Mapper(componentModel = "Spring", uses = {ProductMapper.class, MenuItem.class})
public interface DishMapper {

    DishDTOId mapToDtoId(Dish dish);

    DishDTOExtendedd mapToDtoExtended(Dish dish);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    Dish mapToEntity(DishDTOExtendedd dtoExtendedd);
}
