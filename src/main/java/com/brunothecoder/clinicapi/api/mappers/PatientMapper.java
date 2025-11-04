package com.brunothecoder.clinicapi.api.mappers;

import com.brunothecoder.clinicapi.api.dto.patient.CreatePatientRequest;
import com.brunothecoder.clinicapi.api.dto.patient.PatientResponse;
import com.brunothecoder.clinicapi.domain.entities.Patient;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public Patient toEntity (@Valid CreatePatientRequest request){
        Patient patient = new Patient();
        patient.setName(request.name());
        patient.setCpf(request.cpf());
        return patient;
    }

    public PatientResponse toResponse (Patient patient) {
        return new PatientResponse(
                patient.getId().toString(),
                patient.getName(),
                patient.getCpf(),
                patient.isActive()
        );
    }

}
