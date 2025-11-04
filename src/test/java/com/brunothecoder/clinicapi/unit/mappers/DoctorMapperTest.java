package com.brunothecoder.clinicapi.unit.mappers;

import com.brunothecoder.clinicapi.api.dto.doctor.CreateDoctorRequest;
import com.brunothecoder.clinicapi.api.dto.doctor.DoctorResponse;
import com.brunothecoder.clinicapi.api.mappers.DoctorMapper;
import com.brunothecoder.clinicapi.domain.entities.Doctor;
import com.brunothecoder.clinicapi.domain.entities.Specialty;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoctorMapperTest {

    private final DoctorMapper mapper = new DoctorMapper();

    @Test
    void shouldConvertToEntity(){
        CreateDoctorRequest request = new CreateDoctorRequest(
                "Dr. Mapper",
                Specialty.DERMATOLOGY
        );
        Doctor doctor = mapper.toEntity(request);

        assertEquals("Dr. Mapper", doctor.getName());
        assertEquals(Specialty.DERMATOLOGY, doctor.getSpecialty());
    }

    @Test
    void shouldConvertToResponse() {
        Doctor doctor = new Doctor();
        doctor.setId(UUID.randomUUID());
        doctor.setName("Dr. Response");
        doctor.setSpecialty(Specialty.CARDIOLOGY);
        doctor.setActive(true);

        DoctorResponse response = mapper.toResponse(doctor);

        assertEquals("Dr. Response", response.name());
        assertEquals(Specialty.CARDIOLOGY, response.specialty());
        assertTrue(response.active());
    }
}
