package com.brunothecoder.clinicapi.api.dto.patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreatePatientRequest (

    @NotBlank(message = "Name is required!")
    String name,

    @NotBlank(message = "CPF is required!")
    @Pattern(regexp = "\\d{11}", message = "CPF must have 11 digits")
    @Size(min = 11, max = 11, message = "CPF must have 11 digits!")
    String cpf

){}
