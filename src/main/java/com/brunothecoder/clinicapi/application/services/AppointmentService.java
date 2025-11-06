package com.brunothecoder.clinicapi.application.services;

import com.brunothecoder.clinicapi.api.dto.appointment.CreateAppointmentRequest;
import com.brunothecoder.clinicapi.api.exceptions.ErrorMessages;
import com.brunothecoder.clinicapi.api.mappers.AppointmentMapper;
import com.brunothecoder.clinicapi.domain.entities.Appointment;
import com.brunothecoder.clinicapi.domain.entities.AppointmentStatus;
import com.brunothecoder.clinicapi.domain.entities.Doctor;
import com.brunothecoder.clinicapi.domain.entities.Patient;
import com.brunothecoder.clinicapi.domain.repositories.AppointmentRepository;
import com.brunothecoder.clinicapi.domain.repositories.DoctorRepository;
import com.brunothecoder.clinicapi.domain.repositories.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentMapper mapper;

    public AppointmentService(
            AppointmentRepository repository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            AppointmentMapper mapper) {
        this.repository = repository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.mapper = mapper;
    }

    public Appointment create(CreateAppointmentRequest req) {

        Patient patient = patientRepository
                .findById(req.patientId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.PATIENT_NOT_FOUND));

        Doctor doctor = doctorRepository
                .findById(req.doctorId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.DOCTOR_NOT_FOUND));

        Appointment appointment = mapper.toEntity(req, patient, doctor);

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

    public Appointment start(UUID appointmentId) {

        Appointment appointment = repository.findById(appointmentId)
                .orElseThrow(()-> new EntityNotFoundException((ErrorMessages.APPOINTMENT_NOT_FOUND)));

        if(appointment.getStatus() != AppointmentStatus.SCHEDULED) {
            throw new IllegalArgumentException(ErrorMessages.NOT_STARTABLE_APPOINTMENT);
        }

        appointment.setStartDateTime(LocalDateTime.now());
        return repository.save(appointment);
    }
}
