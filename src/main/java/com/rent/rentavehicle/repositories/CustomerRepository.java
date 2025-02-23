// /**
//  * 
//  */
// package com.rent.rentavehicle.repositories;

// import org.springframework.data.repository.CrudRepository;
// import org.springframework.stereotype.Repository;

// import com.rent.rentavehicle.entity.Customer;

// /**
//  * 
//  */
// @Repository
// public interface CustomerRepository extends CrudRepository<Customer,String> {
// 	Customer findByEmail(String email);	// Custom method to find customer by email

// }

package com.rent.rentavehicle.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rent.rentavehicle.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

    // Find customer by email
    Customer findByEmail(String email);

    // Fetch the last inserted customer to generate the next customer_id
    @Query("SELECT c FROM Customer c ORDER BY c.customerId DESC LIMIT 1")
    Optional<Customer> findLastCustomer();
}
