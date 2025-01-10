package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOBasic;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.EmployeeMapper;
import pl.dodo.eLunchApp.model.Employee;
import pl.dodo.eLunchApp.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
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
        return getAllEntites(employeeRepository,employeeMapper::mapToDtoBasic);
    }

    @Override
    @CacheEvict(cacheNames = "employees", allEntries = true)
    public Result<Void> put(UUID uuid, EmployeeDTOExtended basicDelivererDto) {
        UUID dtoUuid = basicDelivererDto.getEmployeeDTOBasic().getEmployeeDTOId().getUuid();
        if (!dtoUuid.equals(uuid))
            return Result.failure(new eLunchError.InvalidUuid(dtoUuid, uuid));

        Optional<Employee> byUuid = employeeRepository.findByUuid(uuid);
        if (byUuid.isEmpty())
            return Result.failure(new eLunchError.ObjectNotFound(Employee.class));

        byUuid.get().edit(employeeMapper.mapToEntity(basicDelivererDto));
        return Result.success(null);
    }

    @Override
    @CacheEvict(cacheNames = "employees", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,employeeRepository);
    }

    @Override
    public Result<EmployeeDTOExtended> getByUuid(UUID uuid) {
        return getEntityByUuid(uuid,employeeRepository,employeeMapper::mapToDtoExtended, Employee.class);
    }
}
