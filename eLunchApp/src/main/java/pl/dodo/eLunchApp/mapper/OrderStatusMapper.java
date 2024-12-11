package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.OrderStatus.OrderStatusDTOBasic;
import pl.dodo.eLunchApp.model.OrderStatus;

@Mapper(componentModel = "Spring")
public interface OrderStatusMapper {

    OrderStatusDTOBasic mapToDtoBasic(OrderStatus orderStatus);

    @InheritInverseConfiguration(name = "mapToDtoBasic")
    OrderStatus mapToEntity(OrderStatusDTOBasic dtoBasic);
}
