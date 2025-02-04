package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOBasic;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
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
        return getAllEntites(employeeRepository,employeeMapper::mapToDtoBasic);
    }

    @Override
    public void add(EmployeeDTOExtended dtoExtended) {
        addEntity(dtoExtended, employeeRepository, employeeMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "employees", allEntries = true)
    public void edit(UUID uuid, EmployeeDTOExtended basicDelivererDto) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound {
        UUID dtoUuid = basicDelivererDto.getEmployeeDTOBasic().getEmployeeDTOId().getUuid();
        if (!dtoUuid.equals(uuid))
            throw new eLunchError.InvalidUuid(dtoUuid, uuid);

        Employee byUuid = employeeRepository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.ObjectNotFound(Employee.class));

        byUuid.edit(employeeMapper.mapToEntity(basicDelivererDto));
    }

    @Override
    @CacheEvict(cacheNames = "employees", key = "#uuid")
    public void delete(UUID uuid) throws eLunchError.ObjectNotFound {
        deleteEntity(uuid,employeeRepository);
    }

    @Override
    public EmployeeDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid,employeeRepository,employeeMapper::mapToDtoExtended, Employee.class);
    }
}
