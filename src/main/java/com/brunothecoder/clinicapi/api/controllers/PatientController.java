package com.brunothecoder.clinicapi.api.controllers;


import com.brunothecoder.clinicapi.api.dto.patient.CreatePatientRequest;
import com.brunothecoder.clinicapi.api.dto.patient.PatientResponse;
import com.brunothecoder.clinicapi.api.mappers.PatientMapper;
import com.brunothecoder.clinicapi.application.services.PatientService;
import com.brunothecoder.clinicapi.domain.entities.Patient;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/patients")
public class PatientController {

    private final PatientService service;
    private final PatientMapper mapper;

    public PatientController(PatientService service, PatientMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> create (@Valid @RequestBody CreatePatientRequest req) {
        Patient saved = service.create(mapper.toEntity(req));
        return ResponseEntity.created(URI.create("/v1/patients/" + saved.getId()))
                .body(mapper.toResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> list() {
        return ResponseEntity.ok(service.list().stream().map(mapper::toResponse).toList());
    }
}
