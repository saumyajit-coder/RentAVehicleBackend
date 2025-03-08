package com.rent.rentavehicle.entity;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	public String adminId;

	@Column(name = "a_name", nullable = false)
	private String fullName;

	@Column(name = "contact", nullable = false, unique = true)
	private String contactNumber;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "pass", nullable = false)
	private String passwordHash;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	public Admin() {
		this.createdAt = LocalDateTime.now();
	}

	public Admin(String adminId, String fullName, String contactNumber, String email, String password) {
		this.adminId = adminId;
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

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setNewPassword(String password) {
		this.passwordHash = hashPassword(password);
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
