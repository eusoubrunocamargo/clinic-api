package com.brunothecoder.clinicapi.application.services;

import com.brunothecoder.clinicapi.domain.entities.Doctor;
import com.brunothecoder.clinicapi.domain.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public Doctor create(Doctor doctor) {
        return repository.save(doctor);
    }

    public List<Doctor> list() {
        return repository.findAll();
    }
}
