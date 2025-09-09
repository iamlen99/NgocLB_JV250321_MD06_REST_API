package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.CustomerStatusUpdateRequest;
import ra.edu.model.entity.Customer;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Customer>>> getAllCustomers() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list Customers successfully",
                customerService.getAllCustomers(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Customer>> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get customer " + id + " successfully",
                customerService.getCustomerById(id),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Customer>> addCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Add new customer successfully",
                customerService.insertCustomer(customer),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Customer>> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Update customer " + id + " successfully",
                customerService.updateCustomer(id, customer),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Void>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Delete customer " + id + " successfully",
                null,
                null,
                HttpStatus.NO_CONTENT
        ), HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiDataResponse<Customer>> updateCustomerStatus(
            @PathVariable Long id,
            @RequestBody CustomerStatusUpdateRequest request) {
        Customer updatedCustomer = customerService.updateCustomerStatus(id, request.getStatus());
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Update status for customer " + id + " successfully",
                updatedCustomer,
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

}
