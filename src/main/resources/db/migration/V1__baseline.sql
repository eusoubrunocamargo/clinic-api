CREATE TABLE IF NOT EXISTS doctors (
    id BINARY(16) NOT NULL,
    name VARCHAR(120) NOT NULL,
    specialty VARCHAR(40) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_doctors PRIMARY KEY (id),
    CONSTRAINT chk_doctors_specialty CHECK (
        specialty IN (
            'CARDIOLOGY',
            'DERMATOLOGY',
            'PEDIATRICS',
            'ORTHOPEDICS',
            'NEUROLOGY',
            'GYNECOLOGY',
            'PSYCHIATRY',
            'OPHTHALMOLOGY',
            'ONCOLOGY',
            'GENERAL_PRACTICE'
        )
    )
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS patients (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL
) ENGINE=InnoDB;