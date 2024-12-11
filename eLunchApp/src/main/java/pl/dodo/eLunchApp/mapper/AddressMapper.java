package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Address.AddressDTOBasic;
import pl.dodo.eLunchApp.dto.Address.AddressDTOExtended;
import pl.dodo.eLunchApp.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDTOBasic mapToDtoBasic(Address address);

    AddressDTOExtended mapToDtoExtended(Address address);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    Address mapToEntity(AddressDTOExtended dtoExtended);
}
