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
('Durga', '8658232888', 'durga@elitewheel.com', 'securepass1'),
('Saumyajit', '6371792098', 'saumyajit@elitewheel.com', 'securepass2'),
('Jagrat', '9582312907', 'jagrat@elitewheel.com', 'securepass3'),
('Akshat', '8871842515', 'akshat@elitewheel.com', 'securepass4');

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

INSERT INTO customers (customer_id, c_name, contact, email, pass) 
VALUES ('C0001', 'Sneha Iyer', '9456789012', 'sneha@gmail.com', SHA2('customerpass1234', 256));

INSERT INTO customers (customer_id, c_name, contact, email, pass) 
VALUES ('C0002', 'Amit Sharma', '9123456789', 'amit@gmail.com', SHA2('customerpass1231', 256));

INSERT INTO customers (customer_id, c_name, contact, email, pass) 
VALUES ('C0003', 'Priya Verma', '9234567890', 'priya@gmail.com', SHA2('customerpass1232', 256));

INSERT INTO customers (customer_id, c_name, contact, email, pass) 
VALUES ('C0004', 'Rahul Nair', '9345678901', 'rahul@gmail.com', SHA2('customerpass1233', 256));

SELECT * FROM customers;


-- Vehicles Table
CREATE TABLE vehicles (
    vehicle_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_name VARCHAR(255) NOT NULL,
    vehicle_no VARCHAR(50) NOT NULL UNIQUE,
    chassis_no VARCHAR(100) NOT NULL UNIQUE,
    engine_no VARCHAR(100) NOT NULL UNIQUE,
    model_name VARCHAR(255) NOT NULL,
    kilometer_driven INT NOT NULL,
    registration_date DATE NOT NULL,
    fuel_type ENUM('Petrol', 'Diesel', 'CNG', 'Electric', 'Hybrid') NOT NULL,
    financier VARCHAR(255),
    insurance_company VARCHAR(255) NOT NULL,
    insurance_policy_no VARCHAR(100) NOT NULL,
    insurance_valid_upto DATE NOT NULL,
    fitness_valid_upto DATE NOT NULL,
    puc_certificate_no VARCHAR(100) NOT NULL,
    puc_valid_upto DATE NOT NULL,
    registering_authority VARCHAR(255),
    mileage INT NOT NULL,
    vehicle_service_history TEXT,
    vehicle_type ENUM('two_wheeler', 'four_wheeler') NOT NULL,
    category ENUM('e_scooty', 'scooty', 'sports_bike', 'bike', 'sedan', 'hatchback', 'xuv', 'premium') NOT NULL,
    status ENUM('available', 'booked', 'maintenance') DEFAULT 'available',
    price_per_day DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO vehicles (
    owner_name, vehicle_no, chassis_no, engine_no, model_name, kilometer_driven, 
    registration_date, fuel_type, financier, insurance_company, insurance_policy_no, 
    insurance_valid_upto, fitness_valid_upto, puc_certificate_no, puc_valid_upto, 
    registering_authority, mileage, vehicle_service_history, vehicle_type, category, 
    status, price_per_day
) VALUES 
(
    'Rajesh Kumar', 'DL8CAF5678', 'CHS987654321', 'ENG123456789', 'Maruti Swift', 32000, 
    '2021-08-20', 'Petrol', 'SBI Auto Loan', 'Bajaj Allianz', 'POL567890', 
    '2024-08-20', '2025-08-20', 'PUC123456', '2024-11-30', 
    'RTO Delhi', 18, 'Serviced every 6 months', 'four_wheeler', 'hatchback', 
    'available', 1200.00
), 
(
    'Sneha Patil', 'MH14XY6789', 'CHS456789012', 'ENG567890123', 'Hyundai Creta', 28000, 
    '2022-10-10', 'Diesel', 'Axis Bank', 'HDFC Ergo', 'POL987654', 
    '2025-10-10', '2026-10-10', 'PUC678901', '2024-12-20', 
    'RTO Pune', 16, 'Periodic maintenance done', 'four_wheeler', 'xuv', 
    'available', 1800.00
), 
(
    'Arjun Mehta', 'KA03MN2345', 'CHS234567890', 'ENG345678901', 'Royal Enfield Classic 350', 15000, 
    '2023-03-05', 'Petrol', 'HDFC Bank', 'Reliance General', 'POL234567', 
    '2026-03-05', '2027-03-05', 'PUC345678', '2025-06-15', 
    'RTO Bangalore', 35, 'Timely servicing at authorized center', 'two_wheeler', 'sports_bike', 
    'available', 700.00
), 
(
    'Priyanka Das', 'WB20PQ4567', 'CHS678901234', 'ENG789012345', 'Tata Nexon EV', 18000, 
    '2022-12-12', 'Electric', 'ICICI Bank', 'Tata AIG', 'POL345678', 
    '2025-12-12', '2026-12-12', 'PUC789012', '2024-12-31', 
    'RTO Kolkata', 25, 'Battery checkup and software updates done', 'four_wheeler', 'premium', 
    'available', 2000.00
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
    status ENUM('pending', 'confirmed', 'cancelled', 'completed') DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) ON DELETE CASCADE
);

INSERT INTO bookings (
    customer_id, vehicle_id, start_date, end_date, total_price, status
) VALUES 
('C0001', 1, '2025-03-01', '2025-03-05', 6000.00, 'confirmed'),
('C0002', 2, '2025-03-10', '2025-03-15', 9000.00, 'pending'),
('C0003', 3, '2025-04-01', '2025-04-07', 4900.00, 'cancelled'),
('C0004', 4, '2025-04-15', '2025-04-18', 6000.00, 'completed');


 select * from bookings;



-- Vehicle Documents Table
-- CREATE TABLE vehicle_documents (
--     document_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     vehicle_id BIGINT NOT NULL UNIQUE,
--     driving_license_file_path VARCHAR(255) NOT NULL,
--     rc_file_path VARCHAR(255) NOT NULL,
--     insurance_policy_file_path VARCHAR(255) NOT NULL,
--     puc_certificate_file_path VARCHAR(255) NOT NULL,
--     id_proof_file_path VARCHAR(255) NOT NULL,
--     uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) ON DELETE CASCADE
-- );

CREATE TABLE vehicle_documents (
    document_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id BIGINT NOT NULL,
    document_type ENUM('DRIVING_LICENSE', 'RC', 'INSURANCE_POLICY', 'PUC_CERTIFICATE', 'ID_PROOF') NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    uploaded_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id) ON DELETE CASCADE
);


-- INSERT INTO vehicle_documents (
--     vehicle_id, driving_license_file_path, rc_file_path, insurance_policy_file_path, 
--     puc_certificate_file_path, id_proof_file_path
-- ) VALUES 
-- (1, '/documents/vehicle1_dl.pdf', '/documents/vehicle1_rc.pdf', '/documents/vehicle1_insurance.pdf', '/documents/vehicle1_puc.pdf', '/documents/vehicle1_id.pdf'),
-- (2, '/documents/vehicle2_dl.pdf', '/documents/vehicle2_rc.pdf', '/documents/vehicle2_insurance.pdf', '/documents/vehicle2_puc.pdf', '/documents/vehicle2_id.pdf'),
-- (3, '/documents/vehicle3_dl.pdf', '/documents/vehicle3_rc.pdf', '/documents/vehicle3_insurance.pdf', '/documents/vehicle3_puc.pdf', '/documents/vehicle3_id.pdf'),
-- (4, '/documents/vehicle4_dl.pdf', '/documents/vehicle4_rc.pdf', '/documents/vehicle4_insurance.pdf', '/documents/vehicle4_puc.pdf', '/documents/vehicle4_id.pdf');


INSERT INTO vehicle_documents (vehicle_id, document_type, file_path, uploaded_at) 
VALUES 
(1, 'DRIVING_LICENSE', '/documents/license1.pdf', NOW()),
(2, 'RC', '/documents/rc2.pdf', NOW()),
(3, 'INSURANCE_POLICY', '/documents/insurance3.pdf', NOW()),
(4, 'PUC_CERTIFICATE', '/documents/puc4.pdf', NOW());

SELECT * FROM vehicle_documents;


-- Customer Documents Table
-- CREATE TABLE customer_documents (
--     document_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     customer_id VARCHAR(5) NOT NULL UNIQUE,
--     booking_id BIGINT NOT NULL,
--     driving_license_file_path VARCHAR(255) NOT NULL,
--     government_id_file_path VARCHAR(255) NOT NULL,
--     address_proof_file_path VARCHAR(255) NOT NULL,
--     emergency_contact_file_path VARCHAR(255) NOT NULL,
--     international_driving_permit_file_path VARCHAR(255) NOT NULL,
--     passport_file_path VARCHAR(255) NOT NULL,
--     uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
--     FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE
-- );

CREATE TABLE customer_documents (
    document_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id varchar(5) NOT NULL,
    booking_id BIGINT NOT NULL,
    document_type ENUM('DRIVING_LICENSE', 'GOVERNMENT_ID', 'ADDRESS_PROOF', 'EMERGENCY_CONTACT', 'INTERNATIONAL_DRIVING_PERMIT', 'PASSPORT') NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    uploaded_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE
);


-- INSERT INTO customer_documents (
--     customer_id, booking_id, driving_license_file_path, government_id_file_path, 
--     address_proof_file_path, emergency_contact_file_path, 
--     international_driving_permit_file_path, passport_file_path
-- ) VALUES 
-- ('C0001', 1, '/documents/customer1_dl.pdf', '/documents/customer1_id.pdf', 
--  '/documents/customer1_address.pdf', '/documents/customer1_emergency.pdf', 
--  '/documents/customer1_permit.pdf', '/documents/customer1_passport.pdf'),

-- ('C0002', 2, '/documents/customer2_dl.pdf', '/documents/customer2_id.pdf', 
--  '/documents/customer2_address.pdf', '/documents/customer2_emergency.pdf', 
--  '/documents/customer2_permit.pdf', '/documents/customer2_passport.pdf'),

-- ('C0003', 3, '/documents/customer3_dl.pdf', '/documents/customer3_id.pdf', 
--  '/documents/customer3_address.pdf', '/documents/customer3_emergency.pdf', 
--  '/documents/customer3_permit.pdf', '/documents/customer3_passport.pdf'),

-- ('C0004', 4, '/documents/customer4_dl.pdf', '/documents/customer4_id.pdf', 
--  '/documents/customer4_address.pdf', '/documents/customer4_emergency.pdf', 
--  '/documents/customer4_permit.pdf', '/documents/customer4_passport.pdf');

INSERT INTO customer_documents (customer_id, booking_id, document_type, file_path, uploaded_at) 
VALUES 
("C0001", 1, 'DRIVING_LICENSE', '/uploads/documents/dl_1.pdf', NOW()),
("C0002", 2, 'GOVERNMENT_ID', '/uploads/documents/gov_id_2.pdf', NOW()),
("C0003", 3, 'PASSPORT', '/uploads/documents/passport_3.pdf', NOW()),
("C0004", 4, 'ADDRESS_PROOF', '/uploads/documents/address_proof_4.pdf', NOW());
 
 SELECT * FROM customer_documents;
 
show tables;

DROP TABLE review;
DROP TABLE payment;

-- Create Review Table
CREATE TABLE review (
    review_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(255) NOT NULL,
    vehicle_id BIGINT NOT NULL,
    booking_id BIGINT NOT NULL,
    review_text TEXT,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    feedback TEXT,
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Create Payment Table
CREATE TABLE payment (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_id BIGINT NOT NULL,
    stripe_payment_id VARCHAR(255) UNIQUE,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    amount_paid DECIMAL(10,2) NOT NULL,
    payment_method ENUM('CREDIT_CARD', 'DEBIT_CARD', 'NET_BANKING', 'UPI', 'CASH') NOT NULL,
    payment_status ENUM('SUCCEEDED', 'FAILED', 'PENDING') NOT NULL
);
-- Increase size to store AWS S3 URLs  
ALTER TABLE customer_documents MODIFY COLUMN file_path VARCHAR(500);  
ALTER TABLE vehicle_documents MODIFY COLUMN file_path VARCHAR(500);  

-- Rename file_path to file_url in customer_documents  
ALTER TABLE customer_documents CHANGE COLUMN file_path file_url VARCHAR(500) NOT NULL;  

-- Describe the table structure  
DESC customer_documents;  

-- Rename file_path to file_url in vehicle_documents  
ALTER TABLE vehicle_documents CHANGE COLUMN file_path file_url VARCHAR(500) NOT NULL;  

-- Describe the table structure  
DESC vehicle_documents;
ALTER TABLE elite_wheels.vehicle_documents 
CHANGE COLUMN document_type_old document_type_old ENUM('DRIVING_LICENSE', 'RC', 'INSURANCE_POLICY', 'PUC_CERTIFICATE', 'ID_PROOF', 'VEHICLE_IMAGE') NOT NULL ;