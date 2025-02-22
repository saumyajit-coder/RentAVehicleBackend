package com.rent.rentavehicle.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
public class Admin {
    
    @Id
    private String adminId;

    @Column(name = "a_name", nullable = false)
    private String fullName;

    @Column(name = "contact", nullable = false, unique = true)
    private String contactNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "pass", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Admin() {
    }

    public Admin(String adminId, String fullName, String contactNumber, String email, String passwordHash, LocalDateTime createdAt) {
        this.adminId = adminId;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    public String getAdminId() 
    	{ 
    		return adminId; 
    	}
    public void setAdminId(String adminId) 
    	{ 
    		this.adminId = adminId; 
    	}

    public String getFullName() 
    	{ 
    		return fullName; 
    	}
    public void setFullName(String fullName) 
    	{ 
    		this.fullName = fullName; 
    	}

    public String getContactNumber() 
    	{ 
    		return contactNumber; 
    	}
    public void setContactNumber(String contactNumber) 
    	{ 
    		this.contactNumber = contactNumber; 
    	}

    public String getEmail() 
    	{ 
    		return email; 
    	}
    public void setEmail(String email) 
    	{ 
    		this.email = email; 
    	}

    public String getPasswordHash() 
    	{ 
    		return passwordHash; 
    	}
    public void setPasswordHash(String passwordHash) 
    	{ 
    		this.passwordHash = passwordHash; 
    	}

    public LocalDateTime getCreatedAt() 
    	{ 
    		return createdAt; 
    	}
    public void setCreatedAt(LocalDateTime createdAt) 
    	{ 
    		this.createdAt = createdAt; 
    	}
}
