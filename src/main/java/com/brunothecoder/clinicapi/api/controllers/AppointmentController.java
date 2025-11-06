package com.brunothecoder.clinicapi.api.controllers;

import com.brunothecoder.clinicapi.api.dto.appointment.AppointmentResponse;
import com.brunothecoder.clinicapi.api.dto.appointment.CreateAppointmentRequest;
import com.brunothecoder.clinicapi.api.exceptions.ErrorMessages;
import com.brunothecoder.clinicapi.api.mappers.AppointmentMapper;
import com.brunothecoder.clinicapi.application.services.AppointmentService;
import com.brunothecoder.clinicapi.domain.entities.Appointment;
import com.brunothecoder.clinicapi.domain.entities.Doctor;
import com.brunothecoder.clinicapi.domain.entities.Patient;
import com.brunothecoder.clinicapi.domain.repositories.DoctorRepository;
import com.brunothecoder.clinicapi.domain.repositories.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    private final AppointmentService service;
    private final AppointmentMapper mapper;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;


    public AppointmentController(
            AppointmentService service,
            AppointmentMapper mapper,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository
            ) {
        this.service = service;
        this.mapper = mapper;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> list() {
        return ResponseEntity.ok(service
                .list()
                .stream()
                .map(mapper::toResponse)
                .toList());
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> create (@Valid @RequestBody CreateAppointmentRequest req) {
        Appointment saved = service.create(req);
        return ResponseEntity.created(URI.create("/v1/appointments" + saved.getId()))
                .body(mapper.toResponse(saved));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<AppointmentResponse> cancel (@PathVariable(value = "id") UUID id) {
        Appointment cancelled = service.cancel(id);
        return ResponseEntity.ok(mapper.toResponse(cancelled));
    }

    @PatchMapping("/{id}/start")
    public ResponseEntity<AppointmentResponse> start (@PathVariable(value = "id") UUID id) {
        Appointment started = service.start(id);
        return ResponseEntity.ok(mapper.toResponse(started));
    }
}
