package com.brunothecoder.clinicapi.api.dto.doctor;

import com.brunothecoder.clinicapi.domain.entities.Specialty;

public record DoctorResponse(
        String id,
        String name,
        Specialty specialty,
        boolean active
) {
}
