package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOBasic;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOExtended;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOId;
import pl.dodo.eLunchApp.model.Employee;

@Mapper(componentModel = "Spring", uses = {PersonalDataMapper.class, LoginDataMapper.class})
public interface EmployeeMapper {

    EmployeeDTOId mapToDtoId(Employee employee);

    EmployeeDTOBasic mapToDtoBasic(Employee employee);

    EmployeeDTOExtended mapToDtoExtended(Employee employee);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    Employee mapToEntity(EmployeeDTOExtended employeeDTOExtended);
}
