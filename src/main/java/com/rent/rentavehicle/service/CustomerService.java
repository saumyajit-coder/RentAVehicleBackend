package com.rent.rentavehicle.service;

import java.util.List;

import com.rent.rentavehicle.entity.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer getCustomerById(String customerId);
    Customer getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
    void deleteCustomer(String customerId);
}
