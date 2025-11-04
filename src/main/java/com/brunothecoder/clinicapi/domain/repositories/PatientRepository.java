package com.brunothecoder.clinicapi.domain.repositories;

import com.brunothecoder.clinicapi.domain.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository <Patient, UUID> {
    boolean existsByIdAndActiveTrue(UUID id);
}
