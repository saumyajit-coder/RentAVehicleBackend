package com.rent.rentavehicle.entity;

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
    public Long vehicleId;


    @Column(nullable = false, unique = true)
    private String vehicleNo;



    @Column(nullable = false)
    private String modelName;

    @Column(nullable = false)
    private int kilometerDriven;


    @Column(nullable = false)
    private String fuelType;
  

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
        this.vehicleNo = vehicleNo;
        this.modelName = modelName;
        this.kilometerDriven = kilometerDriven;
        this.fuelType = fuelType;
        this.vehicleType = vehicleType;
        this.category = category;
        this.status = status;
        this.pricePerDay = pricePerDay;
    }

    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }


    public String getVehicleNo() { return vehicleNo; }
    public void setVehicleNo(String vehicleNo) { this.vehicleNo = vehicleNo; }


    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }

    public int getKilometerDriven() { return kilometerDriven; }
    public void setKilometerDriven(int kilometerDriven) { this.kilometerDriven = kilometerDriven; }


    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
 

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(Double pricePerDay) { this.pricePerDay = pricePerDay; }
}