package ra.edu.model.service;

import ra.edu.model.dto.LoginDTO;
import ra.edu.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    boolean registerCustomer(Customer customer);
    Customer loginCustomer(LoginDTO loginDTO);
    List<Customer> getAllCustomers();
    boolean emailExist(String email);

}
