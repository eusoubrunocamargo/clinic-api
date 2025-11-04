package com.brunothecoder.clinicapi.domain.repositories;

import com.brunothecoder.clinicapi.domain.entities.Doctor;
import com.brunothecoder.clinicapi.domain.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository <Doctor, UUID> {
    boolean existsByIdAndActiveTrue(UUID activeId);
    List<Doctor> findBySpecialtyAndActiveTrue(Specialty specialty);
}
