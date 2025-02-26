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
import org.springframework.data.domain.PageRequest;
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
        // Ensure full name is provided
        if (customer.getFullName() == null || customer.getFullName().isEmpty()) {
            throw new IllegalArgumentException("Full name is required");
        }

        // Ensure contact number is provided
        if (customer.getContactNumber() == null || customer.getContactNumber().isEmpty()) {
            throw new IllegalArgumentException("Contact number is required");
        }

        // Generate unique customer_id
        customer.setCustomerId(generateCustomerId());

        // Hash the password before saving
        customer.setPassword(hashPassword(customer.getPasswordHash()));

        // Debugging - Print customer details before saving
        System.out.println("Saving customer: " + customer);

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
    @Override
    public String generateCustomerId() {
        List<Customer> lastCustomers = customerRepository.findLastCustomer(PageRequest.of(0, 1));
        Customer lastCustomer = lastCustomers.isEmpty() ? null : lastCustomers.get(0);

        int nextId = (lastCustomer != null)
                ? Integer.parseInt(lastCustomer.getCustomerId().substring(1)) + 1
                : 1;

        System.out.println("ID: " + nextId);
        return String.format("C%04d", nextId);
    }

    // Hash password using BCrypt
    private String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
