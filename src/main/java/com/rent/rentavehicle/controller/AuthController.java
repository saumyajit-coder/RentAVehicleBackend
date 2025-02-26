package com.rent.rentavehicle.controller;

import com.rent.rentavehicle.entity.Admin;
import com.rent.rentavehicle.entity.Customer;
import com.rent.rentavehicle.repositories.AdminRepository;
import com.rent.rentavehicle.repositories.CustomerRepository;
import com.rent.rentavehicle.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Hashes password using SHA-256 (Same as database hashing)
     */
    private String hashPasswordSHA256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Admin Login - SHA-256 Based Authentication
     */
    @PostMapping("/admin/login")
    public Map<String, String> adminLogin(@RequestBody Admin admin) {
        Admin dbAdmin = adminRepository.findByEmail(admin.getEmail());

        if (dbAdmin != null && hashPasswordSHA256(admin.getPasswordHash()).equals(dbAdmin.getPasswordHash())) {
            String token = jwtUtil.generateToken(admin.getEmail(), "admin");
            return createResponse("success", token);
        }
        return createResponse("error", "Invalid credentials");
    }

    /**
     * Customer Login - SHA-256 Based Authentication
     */
    @PostMapping("/customer/login")
    public Map<String, String> customerLogin(@RequestBody Customer customer) {
        Customer dbCustomer = customerRepository.findByEmail(customer.getEmail());

        if (dbCustomer != null && hashPasswordSHA256(customer.getPasswordHash()).equals(dbCustomer.getPasswordHash())) {
            String token = jwtUtil.generateToken(customer.getEmail(), "customer");
            return createResponse("success", token);
        }
        return createResponse("error", "Invalid credentials");
    }

    /**
     * Helper method to create response messages
     */
    private Map<String, String> createResponse(String status, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        return response;
    }
}
