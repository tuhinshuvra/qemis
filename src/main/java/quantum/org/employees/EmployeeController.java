package quantum.org.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired private EmployeeService employeeService;

    @GetMapping("/employees")
    public String showEmployeeList(Model model) {
        List<Employees> allEmployee = employeeService.findAll();
        model.addAttribute("allEmployee", allEmployee);

        return "employees/employees";
    }

    @GetMapping("/employees/new")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employees", new Employees());
        model.addAttribute("pageTitle", "Add New Employee");
        return "employees/addEmployee";
    }

    @PostMapping("/employees/save")
    public String saveUser(Employees employees, RedirectAttributes redirectAttributes) {
        employeeService.save(employees);

        redirectAttributes.addFlashAttribute("message", "Employee added successfully");
        return "redirect:/employees";
    }


    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Employees employees = employeeService.get(id);
            model.addAttribute("employees", employees);
            model.addAttribute("pageTitle", "Edit Employee (ID: " + id + ")");
            return "employees/addEmployee";

        }catch (EmployeeNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "Employee not found");
            return "redirect:/employees";
        }
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            employeeService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Employee deleted successfully");
        }catch (EmployeeNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "Employee not found");
        }
        return "redirect:/employees";
    }
}
