package ra.exercise.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exercise.model.entity.Employee;
import ra.exercise.model.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public boolean addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
