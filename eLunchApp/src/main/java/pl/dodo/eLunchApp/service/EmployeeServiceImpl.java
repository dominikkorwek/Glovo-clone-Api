package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOBasic;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.mapper.EmployeeMapper;
import pl.dodo.eLunchApp.model.Employee;
import pl.dodo.eLunchApp.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "employees")
@RequiredArgsConstructor
public class EmployeeServiceImpl extends BaseService implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Cacheable(cacheNames = "employees")
    public List<EmployeeDTOBasic> getAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    @CacheEvict(cacheNames = "employees", allEntries = true)
    public Result<Void> put(UUID uuid, EmployeeDTOExtended basicDelivererDto) {
        //todo
    }

    @Override
    @CacheEvict(cacheNames = "employees", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,employeeRepository);
    }

    @Override
    public Result<EmployeeDTOExtended> getByUuid(UUID uuid) {
        return getByUuid(uuid,employeeRepository,employeeMapper::mapToDtoExtended, Employee.class);
    }
}
