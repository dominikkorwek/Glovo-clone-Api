package pl.dodo.eLunchApp.service;


import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOBasic;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<EmployeeDTOBasic> getAll();
    Result<Void> put(UUID uuid, EmployeeDTOExtended dtoExtended);
    Result<Void> delete(UUID uuid);
    Result<EmployeeDTOExtended> getByUuid(UUID uuid);
}
