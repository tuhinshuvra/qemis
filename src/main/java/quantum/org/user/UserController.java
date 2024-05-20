package quantum.org.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService userService;

    @GetMapping("/users")
    public String showEmployeeList(Model model) {
        List<User> allUser = userService.findAll();
        model.addAttribute("allUser", allUser);

        return "users/users";
    }

    @GetMapping("/users/new")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "users/add_user";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        userService.save(user);

        redirectAttributes.addFlashAttribute("message", "User added successfully");
        return "redirect:/users";
    }


    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "users/add_user";

        }catch (UserNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "User not found");
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("message", "User deleted successfully");
        }catch (UserNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "User not found");
        }
        return "redirect:/users";
    }
}
