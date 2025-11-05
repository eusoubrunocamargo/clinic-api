package com.brunothecoder.clinicapi.api.dto.appointment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateAppointmentRequest(
        UUID patientId,
        UUID doctorId,
        LocalDateTime scheduledDateTime,
        BigDecimal fee
        ) {
}
