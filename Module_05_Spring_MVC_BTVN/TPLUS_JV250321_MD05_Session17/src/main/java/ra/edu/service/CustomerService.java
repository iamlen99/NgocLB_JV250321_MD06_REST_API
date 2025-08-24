package ra.edu.service;

import ra.edu.model.entity.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer save(Customer customer);
    Customer login(String username, String password);
    boolean checkValidUsername(String username);
    boolean checkValidEmail(String email);
    boolean checkValidPhone(String phone);
    Customer findByUsername(String username);
}
