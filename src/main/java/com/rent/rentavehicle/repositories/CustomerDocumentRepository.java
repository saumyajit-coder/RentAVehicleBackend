/**
 * 
 */
package com.rent.rentavehicle.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rent.rentavehicle.entity.CustomerDocument;

/**
 * 
 */
@Repository
public interface CustomerDocumentRepository extends CrudRepository<CustomerDocument, Long> {
	List<CustomerDocument> findByCustomerCustomerId(String customerId); // Get all documents for a customer

}
