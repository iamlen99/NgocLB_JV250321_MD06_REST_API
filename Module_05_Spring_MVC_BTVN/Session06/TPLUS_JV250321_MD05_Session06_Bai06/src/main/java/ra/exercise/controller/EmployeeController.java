package ra.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.exercise.model.entity.Employee;
import ra.exercise.model.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/employee"})
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String getListEmployee(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employeeList";
    }

    @GetMapping("/goToAddPage")
    public String addPage() {
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(
            @RequestParam("name") String employeeName,
            @RequestParam("email") String employeeEmail,
            @RequestParam("position") String position,
            Model model) {
        Employee employee = new Employee();
        employee.setName(employeeName);
        employee.setEmail(employeeEmail);
        employee.setPosition(position);
        if(employeeService.addEmployee(employee)){
            model.addAttribute("message", "Them nhan vien thanh cong");
            return "redirect:/";
        }
        model.addAttribute("employee", employee);
        model.addAttribute("message", "Co loi trong qua trinh them nhan vien, vui long thu lai");
        return "redirect:/goToAddPage";
    }
}
