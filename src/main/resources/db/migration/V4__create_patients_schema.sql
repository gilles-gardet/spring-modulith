-- Create patients schema
CREATE SCHEMA IF NOT EXISTS patients;

-- Create patient table
CREATE TABLE patients.patient (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    social_security_number VARCHAR(255),
    phone_number VARCHAR(255),
    email VARCHAR(255),
    hospital_id UUID,
    service_id UUID
);

-- Create medical_record table
CREATE TABLE patients.medical_record (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID,
    doctor_id UUID,
    record_date TIMESTAMP,
    diagnosis TEXT,
    treatment TEXT,
    notes TEXT,
    follow_up_required BOOLEAN DEFAULT false
);

-- Create medication table
CREATE TABLE patients.medication (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID,
    name VARCHAR(255) NOT NULL,
    dosage VARCHAR(255),
    frequency VARCHAR(255),
    start_date DATE,
    end_date DATE,
    prescribing_doctor_id UUID,
    instructions TEXT
);

-- Create indexes for better performance
CREATE INDEX idx_patient_hospital_id ON patients.patient(hospital_id);
CREATE INDEX idx_patient_service_id ON patients.patient(service_id);
CREATE INDEX idx_patient_ssn ON patients.patient(social_security_number);
CREATE INDEX idx_patient_name ON patients.patient(firstname, lastname);
CREATE INDEX idx_medical_record_patient_id ON patients.medical_record(patient_id);
CREATE INDEX idx_medical_record_doctor_id ON patients.medical_record(doctor_id);
CREATE INDEX idx_medical_record_date ON patients.medical_record(record_date);
CREATE INDEX idx_medication_patient_id ON patients.medication(patient_id);
CREATE INDEX idx_medication_doctor_id ON patients.medication(prescribing_doctor_id);
CREATE INDEX idx_medication_end_date ON patients.medication(end_date);