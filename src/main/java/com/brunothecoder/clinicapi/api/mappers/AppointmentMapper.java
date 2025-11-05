package com.brunothecoder.clinicapi.api.mappers;

import com.brunothecoder.clinicapi.api.dto.appointment.AppointmentResponse;
import com.brunothecoder.clinicapi.api.dto.appointment.CreateAppointmentRequest;
import com.brunothecoder.clinicapi.api.dto.doctor.CreateDoctorRequest;
import com.brunothecoder.clinicapi.api.dto.doctor.DoctorResponse;
import com.brunothecoder.clinicapi.domain.entities.Appointment;
import com.brunothecoder.clinicapi.domain.entities.AppointmentStatus;
import com.brunothecoder.clinicapi.domain.entities.Doctor;
import com.brunothecoder.clinicapi.domain.entities.Patient;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public Appointment toEntity (CreateAppointmentRequest request, Patient patient, Doctor doctor) {
        return Appointment.builder()
                .scheduledDateTime(request.scheduledDateTime())
                .fee(request.fee())
                .status(AppointmentStatus.SCHEDULED)
                .patient(patient)
                .doctor(doctor)
                .build();
    }

    public AppointmentResponse toResponse (Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getScheduledDateTime(),
                appointment.getStartDateTime(),
                appointment.getEndDateTime(),
                appointment.getStatus(),
                appointment.getPatient().getName(),
                appointment.getDoctor().getName(),
                appointment.getDoctor().getCrm(),
                appointment.getFee()
        );
    }

}
