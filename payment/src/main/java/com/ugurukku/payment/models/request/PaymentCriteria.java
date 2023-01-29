package com.ugurukku.payment.models.request;

import java.math.BigDecimal;

public record PaymentCriteria(BigDecimal amountFrom, BigDecimal amountTo,String description) {
}
