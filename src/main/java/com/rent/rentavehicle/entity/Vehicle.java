package com.rent.rentavehicle.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    // @Column(nullable = false)
    // private String ownerName;

    @Column(nullable = false, unique = true)
    private String vehicleNo;

    // @Column(nullable = false, unique = true)
    // private String chassisNo;

    // @Column(nullable = false, unique = true)
    // private String engineNo;

    @Column(nullable = false)
    private String modelName;

    @Column(nullable = false)
    private int kilometerDriven;

    // @Column(nullable = false)
    // private LocalDate registrationDate;

    @Column(nullable = false)
    private String fuelType;

    // @Column(nullable = false)
    // private String insuranceCompany;

    // @Column(nullable = false)
    // private String insurancePolicyNo;

    // @Column(nullable = false)
    // private LocalDate insuranceValidUpto;

    // @Column(nullable = false)
    // private LocalDate fitnessValidUpto;

    // @Column(nullable = false)
    // private String pucCertificateNo;

    // @Column(nullable = false)
    // private LocalDate pucValidUpto;

    // @Column(nullable = false)
    // private String registeringAuthority;

    // private int mileage;

    // private String vehicleServiceHistory;

    @Column(nullable = false)
    private String vehicleType;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double pricePerDay;

    // Default Constructor
    public Vehicle() {}

    // Parameterized Constructor
    public Vehicle(Long vehicleId, String vehicleNo,String modelName, 
                   int kilometerDriven,String fuelType, 
                   String vehicleType, String category, String status, Double pricePerDay) {
        this.vehicleId = vehicleId;
        // this.ownerName = ownerName;
        this.vehicleNo = vehicleNo;
        // this.chassisNo = chassisNo;
        // this.engineNo = engineNo;
        this.modelName = modelName;
        this.kilometerDriven = kilometerDriven;
        // this.registrationDate = registrationDate;
        this.fuelType = fuelType;
        // this.insuranceCompany = insuranceCompany;
        // this.insurancePolicyNo = insurancePolicyNo;
        // this.insuranceValidUpto = insuranceValidUpto;
        // this.fitnessValidUpto = fitnessValidUpto;
        // this.pucCertificateNo = pucCertificateNo;
        // this.pucValidUpto = pucValidUpto;
        // this.registeringAuthority = registeringAuthority;
        // this.mileage = mileage;
        // this.vehicleServiceHistory = vehicleServiceHistory;
        this.vehicleType = vehicleType;
        this.category = category;
        this.status = status;
        this.pricePerDay = pricePerDay;
    }

    

    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }

    // public String getOwnerName() { return ownerName; }
    // public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getVehicleNo() { return vehicleNo; }
    public void setVehicleNo(String vehicleNo) { this.vehicleNo = vehicleNo; }

    // public String getChassisNo() { return chassisNo; }
    // public void setChassisNo(String chassisNo) { this.chassisNo = chassisNo; }

    // public String getEngineNo() { return engineNo; }
    // public void setEngineNo(String engineNo) { this.engineNo = engineNo; }

    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }

    public int getKilometerDriven() { return kilometerDriven; }
    public void setKilometerDriven(int kilometerDriven) { this.kilometerDriven = kilometerDriven; }

    // public LocalDate getRegistrationDate() { return registrationDate; }
    // public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }

    // public String getInsuranceCompany() { return insuranceCompany; }
    // public void setInsuranceCompany(String insuranceCompany) { this.insuranceCompany = insuranceCompany; }

    // public String getInsurancePolicyNo() { return insurancePolicyNo; }
    // public void setInsurancePolicyNo(String insurancePolicyNo) { this.insurancePolicyNo = insurancePolicyNo; }

    // public LocalDate getInsuranceValidUpto() { return insuranceValidUpto; }
    // public void setInsuranceValidUpto(LocalDate insuranceValidUpto) { this.insuranceValidUpto = insuranceValidUpto; }

    // public LocalDate getFitnessValidUpto() { return fitnessValidUpto; }
    // public void setFitnessValidUpto(LocalDate fitnessValidUpto) { this.fitnessValidUpto = fitnessValidUpto; }

    // public String getPucCertificateNo() { return pucCertificateNo; }
    // public void setPucCertificateNo(String pucCertificateNo) { this.pucCertificateNo = pucCertificateNo; }

    // public LocalDate getPucValidUpto() { return pucValidUpto; }
    // public void setPucValidUpto(LocalDate pucValidUpto) { this.pucValidUpto = pucValidUpto; }

    // public String getRegisteringAuthority() { return registeringAuthority; }
    // public void setRegisteringAuthority(String registeringAuthority) { this.registeringAuthority = registeringAuthority; }

    // public int getMileage() { return mileage; }
    // public void setMileage(int mileage) { this.mileage = mileage; }

    // public String getVehicleServiceHistory() { return vehicleServiceHistory; }
    // public void setVehicleServiceHistory(String vehicleServiceHistory) { this.vehicleServiceHistory = vehicleServiceHistory; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(Double pricePerDay) { this.pricePerDay = pricePerDay; }
}