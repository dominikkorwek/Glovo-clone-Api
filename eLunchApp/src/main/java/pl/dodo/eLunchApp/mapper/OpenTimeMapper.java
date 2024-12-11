package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOBasic;
import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOExtended;
import pl.dodo.eLunchApp.model.OpenTime;

@Mapper(componentModel = "Spring", uses = {PeriodTimeMapper.class, RestaurantMapper.class})
public interface OpenTimeMapper {

    OpenTimeDTOBasic mapToDtoBasic(OpenTime openTime);

    OpenTimeDTOExtended mapToDtoExtended(OpenTime openTime);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    OpenTime mapToEntity(OpenTimeDTOExtended dtoExtended);

    void updateFromDto(OpenTimeDTOExtended openTimeDTOExtended, OpenTime openTime);
}
