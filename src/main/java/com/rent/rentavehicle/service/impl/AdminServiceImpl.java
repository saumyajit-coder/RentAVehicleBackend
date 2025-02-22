package com.rent.rentavehicle.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.rentavehicle.entity.Admin;
import com.rent.rentavehicle.repositories.AdminRepository;
import com.rent.rentavehicle.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(String adminId) {
        Optional<Admin> admin = adminRepository.findById(adminId);
        return admin.orElse(null);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return (List<Admin>) adminRepository.findAll();
    }

        @Override
    public boolean deleteAdmin(String adminId) {
        if (adminRepository.existsById(adminId)) {  
            adminRepository.deleteById(adminId);
            return true;
        }
        return false;
    }
	

}
