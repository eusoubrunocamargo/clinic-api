package com.brunothecoder.clinicapi.unit.services;

import com.brunothecoder.clinicapi.application.services.DoctorService;
import com.brunothecoder.clinicapi.domain.entities.Doctor;
import com.brunothecoder.clinicapi.domain.entities.Specialty;
import com.brunothecoder.clinicapi.domain.repositories.DoctorRepository;
import org.junit.jupiter.api.Test;

import javax.print.Doc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DoctorServiceTest {

    private final DoctorRepository repository = mock(DoctorRepository.class);
    private final DoctorService service = new DoctorService(repository);

    @Test
    void shouldCreateDoctor() {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Test Service");
        doctor.setSpecialty(Specialty.CARDIOLOGY);
        doctor.setActive(true);

        when(repository.save(doctor)).thenReturn(doctor);

        Doctor result = service.create(doctor);

        assertEquals("Dr. Test Service", result.getName());
        assertEquals(Specialty.CARDIOLOGY, result.getSpecialty());
        assertTrue(result.isActive());
        verify(repository).save(doctor);
    }

    @Test
    void shouldListDoctors() {
        Doctor d1 = new Doctor();
        Doctor d2 = new Doctor();

        when(repository.findAll()).thenReturn(List.of(d1, d2));

        List<Doctor> result = service.list();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }
}
