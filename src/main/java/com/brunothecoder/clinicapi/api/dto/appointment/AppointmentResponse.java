package com.brunothecoder.clinicapi.api.dto.appointment;

import com.brunothecoder.clinicapi.domain.entities.AppointmentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponse(
        UUID id,
        LocalDateTime scheduledDateTime,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        AppointmentStatus status,
        String patientName,
        String doctorName,
        String doctorCrm,
        BigDecimal fee
) {}
