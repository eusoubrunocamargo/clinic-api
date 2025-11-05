package com.brunothecoder.clinicapi.application.services;

import com.brunothecoder.clinicapi.api.exceptions.ErrorMessages;
import com.brunothecoder.clinicapi.domain.entities.Appointment;
import com.brunothecoder.clinicapi.domain.entities.AppointmentStatus;
import com.brunothecoder.clinicapi.domain.repositories.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }
    public Appointment create(Appointment appointment) {
        return repository.save(appointment);
    }
    public List<Appointment> list() {
        return repository.findAll();
    }

    public Appointment cancel(UUID appointmentId) {

        Appointment appointment = repository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.APPOINTMENT_NOT_FOUND));

        if(appointment.getStatus() != AppointmentStatus.SCHEDULED) {
            throw new IllegalArgumentException(ErrorMessages.NOT_CANCELABLE_APPOINTMENT);
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);
        return repository.save(appointment);
    }
}
