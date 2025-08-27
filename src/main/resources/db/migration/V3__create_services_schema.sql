-- Create services schema
CREATE SCHEMA IF NOT EXISTS services;

-- Create medical_service table
CREATE TABLE services.medical_service (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    hospital_id UUID,
    head_of_service VARCHAR(255),
    bed_count INTEGER DEFAULT 0,
    specialty VARCHAR(255),
    description TEXT,
    location VARCHAR(255)
);

-- Create consultation table
CREATE TABLE services.consultation (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID,
    doctor_id UUID,
    service_id UUID,
    date_time TIMESTAMP,
    diagnosis TEXT,
    treatment TEXT,
    notes TEXT
);

-- Create equipment table
CREATE TABLE services.equipment (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    model VARCHAR(255),
    serial_number VARCHAR(255),
    purchase_date DATE,
    last_maintenance DATE,
    service_id UUID,
    CONSTRAINT fk_equipment_service FOREIGN KEY (service_id) REFERENCES services.medical_service(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX idx_medical_service_hospital_id ON services.medical_service(hospital_id);
CREATE INDEX idx_consultation_patient_id ON services.consultation(patient_id);
CREATE INDEX idx_consultation_doctor_id ON services.consultation(doctor_id);
CREATE INDEX idx_consultation_service_id ON services.consultation(service_id);
CREATE INDEX idx_consultation_date_time ON services.consultation(date_time);
CREATE INDEX idx_equipment_service_id ON services.equipment(service_id);