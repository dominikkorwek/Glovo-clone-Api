package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOBasic;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOExtended;
import pl.dodo.eLunchApp.model.Deliverer;

@Mapper(componentModel = "Spring", uses = {EmployeeMapper.class,OrderMapper.class})
public interface DelivererMapper {

    DelivererDTOExtended mapToDtoExtended(Deliverer deliverer);

    DelivererDTOBasic mapToDtoBasic(Deliverer deliverer);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    Deliverer mapToEntity(DelivererDTOExtended dtoExtended);
}
