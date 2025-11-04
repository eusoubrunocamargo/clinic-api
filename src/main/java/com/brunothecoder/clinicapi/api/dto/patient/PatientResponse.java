package com.brunothecoder.clinicapi.api.dto.patient;

public record PatientResponse (

        String id,
        String name,
        String cpf,
        boolean active

){}
