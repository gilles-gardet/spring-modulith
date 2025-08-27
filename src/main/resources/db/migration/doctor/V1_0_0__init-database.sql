CREATE TABLE specialty
(
    id                          UUID        NOT NULL,
    name                        varchar     NOT NULL UNIQUE,
    description                 varchar     NULL,
    required_years_training     int         NULL,
    CONSTRAINT specialty_pk PRIMARY KEY (id)
);

CREATE TABLE doctor
(
    id                      UUID        NOT NULL,
    firstname               varchar     NOT NULL,
    lastname                varchar     NOT NULL,
    specialty               varchar     NOT NULL,
    license_number          varchar     NULL,
    phone_number            varchar     NULL,
    email                   varchar     NULL,
    hospital_id             UUID        NULL,
    years_experience        int         NULL,
    CONSTRAINT doctor_pk PRIMARY KEY (id)
);

CREATE TABLE qualification
(
    id                      UUID        NOT NULL,
    title                   varchar     NOT NULL,
    institution             varchar     NULL,
    graduation_date         date        NULL,
    doctor_id               UUID        NOT NULL,
    CONSTRAINT qualification_pk PRIMARY KEY (id)
);

ALTER TABLE qualification
    ADD CONSTRAINT qualification_doctor_fk FOREIGN KEY (doctor_id) REFERENCES doctor (id) ON DELETE CASCADE;