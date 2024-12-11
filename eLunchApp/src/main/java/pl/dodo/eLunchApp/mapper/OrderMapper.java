package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Order.OrderDTOBasic;
import pl.dodo.eLunchApp.dto.Order.OrderDTOExtended;
import pl.dodo.eLunchApp.model.Order;

@Mapper(componentModel = "Spring", uses = {OrderStatusMapper.class,
        UserMapper.class, DelivererMapper.class, RestaurantMapper.class,
        DiscountCodeMapper.class, DeliveryAddressMapper.class})
public interface OrderMapper {

    OrderDTOBasic mapToDtoBasic(Order order);

    OrderDTOExtended mapToDtoExtended(Order order);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    Order mapToEntity(OrderDTOExtended dtoExtended);
}
