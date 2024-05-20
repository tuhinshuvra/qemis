package quantum.org.employees;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employees, Integer> {
public Long countById(Integer id);
}
