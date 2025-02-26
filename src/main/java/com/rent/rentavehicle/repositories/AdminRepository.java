/**
 * 
 */
package com.rent.rentavehicle.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rent.rentavehicle.entity.Admin;

/**
 * 
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {
	Admin findByEmail(String email);
}
