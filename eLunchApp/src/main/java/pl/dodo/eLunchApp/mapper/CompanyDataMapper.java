package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Company.CompanyDataDTOBasic;
import pl.dodo.eLunchApp.dto.Company.CompanyDataDTOExtended;
import pl.dodo.eLunchApp.model.CompanyData;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface CompanyDataMapper {

    CompanyDataDTOBasic mapToDtoBasic(CompanyData companyData);

    CompanyDataDTOExtended mapToDtoExtended(CompanyData companyData);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    CompanyData mapToEntity(CompanyDataDTOExtended dtoExtended);
}
