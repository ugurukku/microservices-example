package com.ugurukku.payment.models.request;

import java.math.BigDecimal;

public record PaymentRequest(BigDecimal amount, String description,String currency) {
}
