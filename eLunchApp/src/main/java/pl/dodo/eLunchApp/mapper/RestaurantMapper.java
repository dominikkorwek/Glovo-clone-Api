package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOBasic;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOExtended;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOId;
import pl.dodo.eLunchApp.model.Restaurant;

@Mapper(componentModel = "Spring", uses = {LoginDataMapper.class,
        CompanyDataMapper.class, OpenTimeMapper.class, OrderMapper.class,
        MenuItemMapper.class, DiscountCodeMapper.class})
public interface RestaurantMapper {

    RestaurantDTOId mapToDtoId(Restaurant restaurant);

    RestaurantDTOBasic mapToDtoBasic(Restaurant restaurant);

    RestaurantDTOExtended mapToDtoExtended(Restaurant restaurant);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    Restaurant mapToEntity(RestaurantDTOExtended dtoExtended);
}
