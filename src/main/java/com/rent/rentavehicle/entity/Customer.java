package com.rent.rentavehicle.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "customer_id", updatable = false, nullable = false, length = 5)
    public String customerId;

    @Column(name = "c_name", nullable = false)
    private String fullName;

    @Column(name = "contact", nullable = false, unique = true)
    private String contactNumber;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "pass", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "role", nullable = false, length = 255)
    private String role;

    public Customer() {
        this.createdAt = LocalDateTime.now();
    }

    public Customer(String fullName, String contactNumber, String email, String password, String address, String role) {
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.email = email;
        setPassword(password); // Use setPassword() to hash the password
        this.address = address;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public void setPassword(String password) {
        this.passwordHash = password;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
