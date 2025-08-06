package ra.exercise;

import org.springframework.stereotype.Component;
import ra.exercise.model.entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Data {

    private List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("Nguyễn Văn A", "a@example.com", "Lập trình viên"),
            new Employee("Trần Thị B", "b@example.com", "Kế toán"),
            new Employee("Lê Văn C", "c@example.com", "Thiết kế"),
            new Employee("Phạm Minh D", "d@example.com", "Quản lý"),
            new Employee("Hoàng Thị E", "e@example.com", "Nhân sự")
    ));

    public List<Employee> getEmployees() {
        return employees;
    }
}
