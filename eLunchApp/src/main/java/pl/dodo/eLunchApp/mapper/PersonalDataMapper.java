package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.PersonalData.PersonalDataDTOBasic;
import pl.dodo.eLunchApp.dto.PersonalData.PersonalDataDTOExtended;
import pl.dodo.eLunchApp.model.PersonalData;

@Mapper(componentModel = "Spring")
public interface PersonalDataMapper {

    PersonalDataDTOBasic mapToDtoBasic(PersonalData personalData);

    PersonalDataDTOExtended mapToDtoExtended(PersonalData personalData);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    PersonalData mapToEntity(PersonalDataDTOExtended dtoExtended);
}
