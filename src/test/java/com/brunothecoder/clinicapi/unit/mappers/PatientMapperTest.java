package com.brunothecoder.clinicapi.unit.mappers;

import com.brunothecoder.clinicapi.api.dto.patient.CreatePatientRequest;
import com.brunothecoder.clinicapi.api.dto.patient.PatientResponse;
import com.brunothecoder.clinicapi.api.mappers.PatientMapper;
import com.brunothecoder.clinicapi.domain.entities.Patient;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientMapperTest {

    private final PatientMapper mapper = new PatientMapper();

    @Test
    void shouldConvertToEntity() {
        CreatePatientRequest request = new CreatePatientRequest("Mary", "00000000011");
        Patient patient = mapper.toEntity(request);
        assertEquals("Mary", patient.getName());
        assertEquals("00000000011", patient.getCpf());
    }

    @Test
    void shouldConvertToResponse() {
        Patient patient = new Patient();
        patient.setId(UUID.randomUUID());
        patient.setName("Kurt");
        patient.setCpf("99999999900");
        patient.setActive(true);

        PatientResponse response = mapper.toResponse(patient);

        assertEquals("Kurt", response.name());
        assertEquals("99999999900", response.cpf());
        assertTrue(response.active());
    }
}
