package com.brunothecoder.clinicapi.api.dto.doctor;

import com.brunothecoder.clinicapi.domain.entities.Specialty;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDoctorRequest (
    @NotBlank String name,
    @NotNull @Enumerated Specialty specialty,
    @NotBlank String crm
){}

