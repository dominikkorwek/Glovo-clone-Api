package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Dish.DishDTOExtended;
import pl.dodo.eLunchApp.dto.Dish.DishDTOId;
import pl.dodo.eLunchApp.model.Dish;

@Mapper(componentModel = "Spring", uses = {ProductMapper.class, MenuItemMapper.class})
public interface DishMapper {

    DishDTOId mapToDtoId(Dish dish);

    DishDTOExtended mapToDtoExtended(Dish dish);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    Dish mapToEntity(DishDTOExtended dtoExtended);
}
