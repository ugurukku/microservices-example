package com.ugurukku.payment.models.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record PaymentRequest(@Max(value = 1000)@Min(value = 1)
                             BigDecimal amount,
                             @NotBlank(message = "Can not be empty") String description,

                             @NotBlank(message = "Can not be empty") String currency) {
}
