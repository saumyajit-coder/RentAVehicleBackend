/**
 * 
 */
package com.rent.rentavehicle.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rent.rentavehicle.entity.Vehicle;

/**
 * 
 */
@Repository
public interface VehicleRepository extends CrudRepository<Vehicle,Long>{
	List<Vehicle> findByStatus(String status);	//Get all available vehicles
}
