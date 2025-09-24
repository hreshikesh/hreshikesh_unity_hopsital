create database module;


use module;


CREATE TABLE `unity_hopsital_signin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `otp` varchar(6) DEFAULT NULL,
  `local_date_time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
)


CREATE TABLE `doctor_specialization` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doctor_specialization` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
)


INSERT INTO doctor_specialization (doctor_specialization) VALUES
('Cardiologist'),
('Dermatologist'),
('Neurologist'),
('Orthopedic'),
('Pediatrician'),
('General Physician'),
('Gastroenterologist'),
('Psychiatrist'),
('Urologist'),
('Nephrologist'),
('ENT Specialist'),
('Anesthesiologist'),
('Radiologist'),
('Surgeon');


CREATE TABLE `doctor_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doctor_name` varchar(45) DEFAULT NULL,
  `doctor_email` varchar(45) DEFAULT NULL,
  `doctor_phone` bigint DEFAULT NULL,
  `doctor_specialization` varchar(45) DEFAULT NULL,
  `doctor_qualification` varchar(45) DEFAULT NULL,
  `doctor_experience` int DEFAULT NULL,
  `image_path` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `doctor_email_UNIQUE` (`doctor_email`)
)

CREATE TABLE `doctor_time_slot` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doctor_speciality` varchar(45) DEFAULT NULL,
  `from_time` varchar(45) DEFAULT NULL,
  `to_time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
)


CREATE TABLE `blood_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_blood_group` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
)


INSERT INTO blood_group (patient_blood_group) VALUES
('A+'),
('A-'),
('B+'),
('B-'),
('AB+'),
('AB-'),
('O+'),
('O-');


CREATE TABLE `doctor_time_slot_update` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doctor_name` varchar(45) DEFAULT NULL,
  `doctor_email` varchar(45) DEFAULT NULL,
  `doctor_time_interval` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
)

CREATE TABLE `patient_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_reg_id` varchar(11) DEFAULT NULL,
  `patient_name` varchar(45) DEFAULT NULL,
  `patient_age` int DEFAULT NULL,
  `blood_group` varchar(5) DEFAULT NULL,
  `patient_email` varchar(45) DEFAULT NULL,
  `patient_phone` bigint DEFAULT NULL,
  `patient_address` varchar(200) DEFAULT NULL,
  `patient_disease` varchar(45) DEFAULT NULL,
  `patient_specialization` varchar(45) DEFAULT NULL,
  `doctor_name` varchar(45) DEFAULT NULL,
  `alloted_time_slot` varchar(45) DEFAULT NULL,
  `doctor_fees` int DEFAULT NULL,
  `saved_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `patient_reg_id_UNIQUE` (`patient_reg_id`)
)