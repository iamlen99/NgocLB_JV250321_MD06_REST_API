package ra.edu.model.repository;

import ra.edu.model.dto.LoginDTO;
import ra.edu.model.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();
    boolean register(Customer customer);
    Customer loginCustomer(LoginDTO loginDTO);
    boolean emailExist(String email);
}
