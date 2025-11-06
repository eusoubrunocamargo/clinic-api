package com.brunothecoder.clinicapi.api.dto.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateAppointmentRequest(
        @NotNull UUID patientId,
        @NotNull UUID doctorId,
        @NotNull LocalDateTime scheduledDateTime,
        @NotBlank BigDecimal fee
        ) {
}
