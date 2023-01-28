package com.ugurukku.payment.models.client;

import java.math.BigDecimal;

public record CountryDto(String name, BigDecimal remainingLimit) {
}
