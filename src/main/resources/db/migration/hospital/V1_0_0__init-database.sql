CREATE TABLE hospital
(
    id                      UUID    NOT NULL,
    name                    varchar NOT NULL,
    address                 varchar NOT NULL,
    phone_number            varchar NULL,
    director                varchar NULL,
    nb_max_services         int     NOT NULL,
    CONSTRAINT hospital_pk PRIMARY KEY (id)
);

CREATE TABLE doctor
(
    id                  UUID    NOT NULL,
    hospital_id         UUID    NOT NULL,
    firstname           varchar NOT NULL,
    lastname            varchar NOT NULL,
    specialty           varchar NOT NULL,
    license_number      varchar NULL,
    phone_number        varchar NULL,
    CONSTRAINT doctor_pk PRIMARY KEY (id)
);

CREATE TABLE treatment
(
    id                  UUID    NOT NULL,
    hospital_id         UUID    NOT NULL,
    name                varchar NOT NULL,
    description         varchar NULL,
    duration_minutes    int     NULL,
    protocol            varchar NULL,
    CONSTRAINT treatment_pk PRIMARY KEY (id)
);

ALTER TABLE doctor
    ADD CONSTRAINT doctor_hospital_fk FOREIGN KEY (hospital_id) REFERENCES hospital (id) ON DELETE CASCADE;
ALTER TABLE treatment
    ADD CONSTRAINT treatment_hospital_fk FOREIGN KEY (hospital_id) REFERENCES hospital (id) ON DELETE CASCADE;