/**
 * 
 */
package com.rent.rentavehicle.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rent.rentavehicle.entity.VehicleDocument;

/**
 * 
 */
@Repository
public interface VehicleDocumentRepository extends CrudRepository<VehicleDocument,Long>{
	 List<VehicleDocument> findByVehicleVehicleId(Long vehicleId);  // Get all documents for a vehicle

}
