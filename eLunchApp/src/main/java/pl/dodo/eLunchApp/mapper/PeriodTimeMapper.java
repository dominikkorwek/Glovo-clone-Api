package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Period.PeriodTimeDTOBasic;
import pl.dodo.eLunchApp.model.PeriodTime;

@Mapper(componentModel = "Spring", uses = {})
public interface PeriodTimeMapper {

    PeriodTimeDTOBasic mapToDtoBasic(PeriodTime periodTime);

    @InheritInverseConfiguration(name = "mapToDtoBasic")
    PeriodTime mapToEntity(PeriodTimeDTOBasic dtoBasic);
}
