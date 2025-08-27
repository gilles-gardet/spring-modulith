CREATE TABLE patient
(
    id                      UUID        NOT NULL,
    firstname               varchar     NOT NULL,
    lastname                varchar     NOT NULL,
    birth_date              date        NOT NULL,
    social_security_number  varchar     NULL,
    phone_number            varchar     NULL,
    email                   varchar     NULL,
    hospital_id             UUID        NULL,
    service_id              UUID        NULL,
    CONSTRAINT patient_pk PRIMARY KEY (id)
);

CREATE TABLE medical_record
(
    id                      UUID        NOT NULL,
    patient_id              UUID        NOT NULL,
    admission_date          timestamp   NOT NULL,
    discharge_date          timestamp   NULL,
    diagnosis               varchar     NULL,
    treatment_notes         varchar     NULL,
    CONSTRAINT medical_record_pk PRIMARY KEY (id)
);

CREATE TABLE medication
(
    id                      UUID        NOT NULL,
    patient_id              UUID        NOT NULL,
    name                    varchar     NOT NULL,
    dosage                  varchar     NOT NULL,
    frequency               varchar     NOT NULL,
    start_date              date        NOT NULL,
    end_date                date        NULL,
    CONSTRAINT medication_pk PRIMARY KEY (id)
);

ALTER TABLE medical_record
    ADD CONSTRAINT medical_record_patient_fk FOREIGN KEY (patient_id) REFERENCES patient (id) ON DELETE CASCADE;
ALTER TABLE medication
    ADD CONSTRAINT medication_patient_fk FOREIGN KEY (patient_id) REFERENCES patient (id) ON DELETE CASCADE;