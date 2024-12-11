package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.DeliveryAddress.DeliveryAddressDTOExtended;
import pl.dodo.eLunchApp.model.DeliveryAddress;

@Mapper(componentModel = "Spring", uses = {AddressMapper.class, UserMapper.class})
public interface DeliveryAddressMapper {

    DeliveryAddressDTOExtended mapToDtoExtended(DeliveryAddress deliveryAddress);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    DeliveryAddress mapToEntity(DeliveryAddressDTOExtended dtoExtended);
}
