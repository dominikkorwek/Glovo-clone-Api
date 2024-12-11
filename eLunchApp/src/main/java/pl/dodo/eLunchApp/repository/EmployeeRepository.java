package pl.dodo.eLunchApp.repository;

import org.springframework.stereotype.Repository;
import pl.dodo.eLunchApp.model.Employee;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee> {
}
