package ra.edu.service;

import ra.edu.model.entity.Customer;
import ra.edu.model.entity.CustomerStatus;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer insertCustomer(Customer customer);
    Customer updateCustomer(Long id,Customer customer);
    Customer updateCustomerStatus(Long id, CustomerStatus status);
    void deleteCustomer(Long id);
}
