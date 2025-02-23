// package com.rent.rentavehicle.entity;

// import java.time.LocalDateTime;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "customers")
// public class Customer {

//     @Id
//     @Column(name = "customer_id", updatable = false, nullable = false)
//     private String customerId;

//     @Column(name = "c_name", nullable = false)
//     private String fullName;

//     @Column(name = "contact", nullable = false, unique = true)
//     private String contactNumber;

//     @Column(nullable = false, unique = true)
//     private String email;

//     @Column(name = "pass", nullable = false)
//     private String passwordHash;

//     @Column(nullable = false)
//     private LocalDateTime createdAt = LocalDateTime.now();

//     public Customer() {
//     }

//     public Customer(String customerId, String fullName, String contactNumber, String email, String passwordHash, LocalDateTime createdAt) {
//         this.customerId = customerId;
//         this.fullName = fullName;
//         this.contactNumber = contactNumber;
//         this.email = email;
//         this.passwordHash = passwordHash;
//         this.createdAt = createdAt;
//     }

//     public String getCustomerId() { return customerId; }
//     public void setCustomerId(String customerId) { this.customerId = customerId; }

//     public String getFullName() { return fullName; }
//     public void setFullName(String fullName) { this.fullName = fullName; }

//     public String getContactNumber() { return contactNumber; }
//     public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }

//     public String getPasswordHash() { return passwordHash; }
//     public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

//     public LocalDateTime getCreatedAt() { return createdAt; }
//     public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
// }
package com.rent.rentavehicle.entity;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    private String customerId;

    @Column(name = "c_name", nullable = false)
    private String fullName;

    @Column(name = "contact", nullable = false, unique = true, length = 20)
    private String contactNumber;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "pass", nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Customer() {
        this.createdAt = LocalDateTime.now(); // Set timestamp
    }

    // Constructor without ID (ID is generated)
    public Customer(String fullName, String contactNumber, String email, String password) {
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.passwordHash = hashPassword(password);
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    // Getters and Setters
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { 
        this.customerId = customerId; 
        System.out.println("CID: " + customerId);
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = hashPassword(passwordHash); }

    public LocalDateTime getCreatedAt() { return createdAt; }
}

