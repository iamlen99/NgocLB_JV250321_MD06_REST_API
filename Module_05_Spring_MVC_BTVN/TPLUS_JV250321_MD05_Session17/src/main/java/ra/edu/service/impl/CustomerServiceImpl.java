package ra.edu.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Customer;
import ra.edu.repository.CustomerRepository;
import ra.edu.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer login(String username, String password) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null && BCrypt.checkpw(password, customer.getPassword())) {
            return customer;
        }
        return null;
    }

    @Override
    public boolean checkValidUsername(String username) {
        return customerRepository.findByUsername(username) == null;
    }

    @Override
    public boolean checkValidEmail(String email) {
        return customerRepository.findByEmail(email) == null;
    }

    @Override
    public boolean checkValidPhone(String phone) {
        return customerRepository.findByPhone(phone) == null;
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

}
