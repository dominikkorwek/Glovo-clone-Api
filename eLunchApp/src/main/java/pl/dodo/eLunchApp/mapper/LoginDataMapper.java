package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.LoginData.LoginDataDTOBasic;
import pl.dodo.eLunchApp.model.LoginData;

@Mapper(componentModel = "Spring")
public interface LoginDataMapper {

    LoginDataDTOBasic mapToDtoBasic(LoginData loginData);

    @InheritInverseConfiguration
    LoginData mapToEntity(LoginDataDTOBasic dtoBasic);
}
