package pl.dodo.eLunchApp.service;


import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOBasic;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<EmployeeDTOBasic> getAll();
    void add(EmployeeDTOExtended dtoExtended);
    void edit(UUID uuid, EmployeeDTOExtended dtoExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound;
    void delete(UUID uuid) throws eLunchError.ObjectNotFound;
    EmployeeDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;
}
