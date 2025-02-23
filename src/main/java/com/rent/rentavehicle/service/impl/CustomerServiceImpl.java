// package com.rent.rentavehicle.service.impl;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.rent.rentavehicle.entity.Customer;
// import com.rent.rentavehicle.repositories.CustomerRepository;
// import com.rent.rentavehicle.service.CustomerService;

// @Service
// public class CustomerServiceImpl implements CustomerService {

//     @Autowired
//     private CustomerRepository customerRepository;

//     @Override
//     public Customer createCustomer(Customer customer) {
//         return customerRepository.save(customer);
//     }

//     @Override
//     public Customer getCustomerById(String customerId) {
//         Optional<Customer> customer = customerRepository.findById(customerId);
//         return customer.orElse(null);
//     }

//     @Override
//     public Customer getCustomerByEmail(String email) {
//         return customerRepository.findByEmail(email);
//     }

//     @Override
//     public List<Customer> getAllCustomers() {
//         return (List<Customer>) customerRepository.findAll();
//     }

//     @Override
//     public void deleteCustomer(String customerId) {
//         customerRepository.deleteById(customerId);
//     }
// }


package com.rent.rentavehicle.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rent.rentavehicle.entity.Customer;
import com.rent.rentavehicle.repositories.CustomerRepository;
import com.rent.rentavehicle.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        // Generate unique customer_id (C0001, C0002, ...)
        customer.setCustomerId(generateCustomerId());

        // Hash the password before saving
        customer.setPasswordHash(hashPassword(customer.getPasswordHash()));

        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    // Generate unique customer ID (C0001, C0002, ...)
    private String generateCustomerId() {
        Optional<Customer> lastCustomer = customerRepository.findLastCustomer();
        int nextId = lastCustomer.map(c -> Integer.parseInt(c.getCustomerId().substring(1)) + 1).orElse(1);
        System.out.println("ID: " + nextId);
        return String.format("C%04d", nextId);
    }

    // Hash password using BCrypt
    private String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}

