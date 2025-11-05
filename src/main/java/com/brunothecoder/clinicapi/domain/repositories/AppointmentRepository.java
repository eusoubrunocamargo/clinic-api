package com.brunothecoder.clinicapi.domain.repositories;

import com.brunothecoder.clinicapi.domain.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
}
