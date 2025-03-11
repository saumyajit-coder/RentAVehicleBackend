CREATE DATABASE ELITE_WHEELS;
USE ELITE_WHEELS;

-- Admin Table
CREATE TABLE admin (
    admin_id VARCHAR(10) PRIMARY KEY,
    a_name VARCHAR(255) NOT NULL,
    contact VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    pass CHAR(64) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Auto-generate Admin ID & Hash Password
DELIMITER //
CREATE TRIGGER before_insert_admin
BEFORE INSERT ON admin
FOR EACH ROW
BEGIN
    DECLARE next_id INT;
    -- Generate new Admin ID
    SELECT COALESCE(MAX(CAST(SUBSTRING(admin_id, 2) AS UNSIGNED)), 0) + 1 INTO next_id FROM admin;
    SET NEW.admin_id = CONCAT('A', LPAD(next_id, 2, '0'));
    
    -- Hash password before insertion
    SET NEW.pass = SHA2(NEW.pass, 256);
END;
//
DELIMITER ;

INSERT INTO admin ( a_name, contact, email, pass) 
VALUES 
('Durga', '8658232888', 'durga@elitewheel.com', 'Securepass@1'),
('Saumyajit', '6371792098','saumyajit@elitewheel.com', 'Securepass@2'),
('Jagrat', '9582312907', 'jagrat@elitewheel.com', 'Securepass@3'),
('Akshat', '8871842515', 'akshat@elitewheel.com', 'Securepass@4');

SELECT * FROM admin;

CREATE TABLE customers (
    customer_id VARCHAR(5) PRIMARY KEY,
    c_name VARCHAR(255) NOT NULL,
    contact VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    pass CHAR(64) NOT NULL,
    address varchar(255),
    role varchar(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM customers;


-- Vehicles Table
CREATE TABLE vehicles (
    vehicle_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    vehicle_no VARCHAR(50) NOT NULL UNIQUE,
    model_name VARCHAR(255) NOT NULL,
    kilometer_driven INT NOT NULL,
    fuel_type ENUM('Petrol', 'Diesel', 'CNG', 'Electric', 'Hybrid') NOT NULL,
    vehicle_type ENUM('two_wheeler', 'four_wheeler') NOT NULL,
    category ENUM('e_scooty', 'scooty', 'sports_bike', 'bike', 'sedan', 'hatchback', 'xuv', 'premium') NOT NULL,
    status ENUM('available', 'booked', 'maintenance') DEFAULT 'available',
    price_per_day DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

 select * from vehicles;
 
  
-- Bookings Table
CREATE TABLE bookings (
    booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(5) NOT NULL,
    vehicle_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    status ENUM('pending', 'confirmed', 'cancelled', 'completed') DEFAULT 'confirmed',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) ON DELETE CASCADE
);

 select * from bookings;

CREATE TABLE vehicle_documents (
    document_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id BIGINT NOT NULL,
    document_type ENUM('DRIVING_LICENSE', 'RC', 'INSURANCE_POLICY', 'PUC_CERTIFICATE', 'ID_PROOF', 'VEHICLE_IMAGE') NOT NULL,
    file_url VARCHAR(500) NOT NULL,
    uploaded_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) ON DELETE CASCADE
);

SELECT * FROM vehicle_documents;

CREATE TABLE customer_documents (
    document_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id varchar(5) NOT NULL,
    booking_id BIGINT NOT NULL,
    document_type ENUM('DRIVING_LICENSE', 'GOVERNMENT_ID', 'ADDRESS_PROOF', 'EMERGENCY_CONTACT', 'INTERNATIONAL_DRIVING_PERMIT', 'PASSPORT') NOT NULL,
    file_url VARCHAR(500) NOT NULL,
    uploaded_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE
);
 
 SELECT * FROM customer_documents;
 drop database ELITE_WHEELS;