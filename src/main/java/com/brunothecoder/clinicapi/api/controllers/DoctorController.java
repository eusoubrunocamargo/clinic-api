package com.brunothecoder.clinicapi.api.controllers;

import com.brunothecoder.clinicapi.api.dto.doctor.CreateDoctorRequest;
import com.brunothecoder.clinicapi.api.dto.doctor.DoctorResponse;
import com.brunothecoder.clinicapi.api.mappers.DoctorMapper;
import com.brunothecoder.clinicapi.application.services.DoctorService;
import com.brunothecoder.clinicapi.domain.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {

    private final DoctorService service;
    private final DoctorMapper mapper;

    public DoctorController (DoctorService service, DoctorMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> list() {
        return ResponseEntity.ok(service
                .list()
                .stream()
                .map(mapper::toResponse)
                .toList());
    }

    @PostMapping
    public ResponseEntity<DoctorResponse> create (@Valid @RequestBody CreateDoctorRequest req) {
        Doctor saved = service.create(mapper.toEntity(req));
        return ResponseEntity.created(URI.create(
                "/v1/doctors" + saved.getId()))
                .body(mapper.toResponse(saved));
    }
}
