package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Order.OrderDTOExtended;
import pl.dodo.eLunchApp.dto.OrderItem.OrderItemDTOBasic;
import pl.dodo.eLunchApp.dto.OrderItem.OrderItemDtoExtended;
import pl.dodo.eLunchApp.model.OrderItem;

@Mapper(componentModel = "Spring", uses = {OrderStatusMapper.class, UserMapper.class,
        DelivererMapper.class, RestaurantMapper.class, DiscountCodeMapper.class,
        DeliveryAddressMapper.class, OrderMapper.class})
public interface OrderItemMapper {

    OrderItemDTOBasic mapToDtoBasic(OrderItem orderItem);

    OrderItemDtoExtended mapToExtended(OrderItem orderItem);

    @InheritInverseConfiguration(name = "mapToExtended")
    OrderItem mapToEntity(OrderDTOExtended dtoExtended);
}
