package com.brunothecoder.clinicapi.unit.services;

import com.brunothecoder.clinicapi.application.services.PatientService;
import com.brunothecoder.clinicapi.domain.entities.Patient;
import com.brunothecoder.clinicapi.domain.repositories.PatientRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatientServiceTest {

    private final PatientRepository repository = mock(PatientRepository.class);
    private final PatientService service = new PatientService(repository);

    @Test
    void shouldCreatePatient() {
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setCpf("11111111100");

        when(repository.save(patient)).thenReturn(patient);

        Patient result = service.create(patient);

        assertEquals("John Doe", result.getName());
        assertEquals("11111111100", result.getCpf());
        verify(repository).save(patient);
    }

    @Test
    void shouldListPatients() {
        Patient p1 = new Patient();
        Patient p2 = new Patient();

        when(repository.findAll()).thenReturn(List.of(p1, p2));

        List<Patient> result = service.list();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }
}
