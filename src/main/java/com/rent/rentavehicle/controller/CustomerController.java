package com.rent.rentavehicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rent.rentavehicle.entity.Customer;
import com.rent.rentavehicle.service.CustomerService;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Create a new customer
    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    // Get customer by ID
    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return customerService.getCustomerById(customerId);
    }

    // Get customer by Email
    @GetMapping("/email/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    // Get all customers
    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Delete customer by ID
    @DeleteMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return "Customer deleted successfully.";
    }
}
