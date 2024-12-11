package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Period.PeriodDTOBasic;
import pl.dodo.eLunchApp.model.Period;

@Mapper(componentModel = "Spring")
public interface PeriodMapper {

    PeriodDTOBasic mapToBasic(Period period);

    @InheritInverseConfiguration(name = "mapToBasic")
    Period mapToEntity(PeriodDTOBasic dtoBasic);
}
