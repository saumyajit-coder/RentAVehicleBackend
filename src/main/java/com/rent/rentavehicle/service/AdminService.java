/**
 * 
 */
package com.rent.rentavehicle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rent.rentavehicle.entity.Admin;

/**
 * 
 */
@Service
public interface AdminService {
	Admin createAdmin(Admin admin);  
    Admin getAdminById(String adminId);  
    Admin getAdminByEmail(String email); 
    List<Admin> getAllAdmins();  
    boolean deleteAdmin(String adminId); 

}
