CREATE TABLE appointments (
    id BINARY(16) NOT NULL,
    scheduled_date_time TIMESTAMP NOT NULL,
    start_date_time TIMESTAMP,
    end_date_time TIMESTAMP,
    fee NUMERIC(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,

    patient_id BINARY(16) NOT NULL,
    doctor_id BINARY(16) NOT NULL,

    CONSTRAINT pk_appointments PRIMARY KEY (id),
    CONSTRAINT fk_appointments_patient FOREIGN KEY (patient_id) REFERENCES patients(id),
    CONSTRAINT fk_appointments_doctor FOREIGN KEY (doctor_id) REFERENCES doctors(id),
    CONSTRAINT chk_status CHECK (status IN ('SCHEDULED','CANCELLED','COMPLETED'))
) ENGINE=InnoDB;