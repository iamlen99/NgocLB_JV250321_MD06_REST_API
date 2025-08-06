package ra.exercise.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.exercise.Data;
import ra.exercise.model.entity.Employee;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private Data data;

    public List<Employee> findAll() {
        return data.getEmployees();
    }

    public boolean save(Employee employee) {
        return data.getEmployees().add(employee);
    }
}
