/**
 * 
 */
package com.rent.rentavehicle.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rent.rentavehicle.entity.Customer;

/**
 * 
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer,String> {
	Customer findByEmail(String email);	// Custom method to find customer by email

}
