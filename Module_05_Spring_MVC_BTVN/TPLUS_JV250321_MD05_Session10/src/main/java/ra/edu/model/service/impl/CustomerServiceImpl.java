package ra.edu.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.dto.LoginDTO;
import ra.edu.model.entity.Customer;
import ra.edu.model.repository.CustomerRepository;
import ra.edu.model.service.CustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean registerCustomer(Customer customer) {
        return customerRepository.register(customer);
    }

    @Override
    public Customer loginCustomer(LoginDTO loginDTO) {
        return customerRepository.loginCustomer(loginDTO);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public boolean emailExist(String email) {
        return customerRepository.emailExist(email);
    }
}
