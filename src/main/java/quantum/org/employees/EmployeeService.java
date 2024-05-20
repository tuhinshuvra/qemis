package quantum.org.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired private EmployeeRepository employeeRepository;

    public List<Employees> findAll() {
        return (List<Employees>) employeeRepository.findAll();
    }

    public void save(Employees employees) {
        employeeRepository.save(employees);
    }

    public Employees get(Integer id) throws EmployeeNotFoundException {
        Optional<Employees> employees = employeeRepository.findById(id);
        if (employees.isPresent()) {
            return employees.get();
        }
        throw new EmployeeNotFoundException("Could not found any employees with the " + id);
    }


    public void delete(Integer id) throws EmployeeNotFoundException {
        Long count = employeeRepository.countById(id);
        if (count == null || count == 0) {
            throw new EmployeeNotFoundException("Could not found any employee with the " + id);
        }
        employeeRepository.deleteById(id);
    }

}
