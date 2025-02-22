package com.rent.rentavehicle.service;

import com.rent.rentavehicle.entity.Customer;
import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer getCustomerById(String customerId);
    Customer getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
    void deleteCustomer(String customerId);
}
