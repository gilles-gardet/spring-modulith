CREATE TABLE medical_service
(
    id                  UUID    NOT NULL,
    name                varchar NOT NULL,
    hospital_id         UUID    NOT NULL,
    head_of_service     varchar NULL,
    bed_count           int     NOT NULL DEFAULT 0,
    specialty           varchar NULL,
    CONSTRAINT medical_service_pk PRIMARY KEY (id)
);

CREATE TABLE consultation
(
    id                      UUID        NOT NULL,
    consultation_date       timestamp   NOT NULL,
    duration_minutes        int         NOT NULL DEFAULT 30,
    reason                  varchar     NULL,
    diagnosis               varchar     NULL,
    service_id              UUID        NOT NULL,
    CONSTRAINT consultation_pk PRIMARY KEY (id)
);

ALTER TABLE consultation
    ADD CONSTRAINT consultation_service_fk FOREIGN KEY (service_id) REFERENCES medical_service (id) ON DELETE CASCADE;