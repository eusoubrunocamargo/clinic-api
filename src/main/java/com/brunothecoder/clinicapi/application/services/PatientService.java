package com.brunothecoder.clinicapi.application.services;

import com.brunothecoder.clinicapi.domain.entities.Patient;
import com.brunothecoder.clinicapi.domain.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient create (Patient patient) {
        return repository.save(patient);
    }

    public List<Patient> list() {
        return repository.findAll();
    }

}
