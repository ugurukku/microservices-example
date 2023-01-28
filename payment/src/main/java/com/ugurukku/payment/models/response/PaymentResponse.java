package com.ugurukku.payment.models.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(Long id, BigDecimal amount, String description, LocalDateTime responseAt) {
}
