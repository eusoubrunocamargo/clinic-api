package com.brunothecoder.clinicapi.api.mappers;

import com.brunothecoder.clinicapi.api.dto.doctor.CreateDoctorRequest;
import com.brunothecoder.clinicapi.api.dto.doctor.DoctorResponse;
import com.brunothecoder.clinicapi.domain.entities.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public Doctor toEntity (CreateDoctorRequest request) {
        Doctor doctor = new Doctor();
        doctor.setName(request.name());
        doctor.setSpecialty(request.specialty());
        doctor.setCrm(request.crm());
        return doctor;
    }

    public DoctorResponse toResponse (Doctor doctor) {
        return new DoctorResponse(
                doctor.getId().toString(),
                doctor.getName(),
                doctor.getSpecialty(),
                doctor.getCrm(),
                doctor.isActive()
        );
    }
}
