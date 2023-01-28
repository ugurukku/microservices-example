package com.ugurukku.payment.mappers;

import com.ugurukku.payment.entities.Payment;
import com.ugurukku.payment.models.request.PaymentRequest;
import com.ugurukku.payment.models.response.PaymentResponse;

public class PaymentMapper {

    public static Payment mapRequestToEntity(PaymentRequest request) {
        return Payment.builder()
                .amount(request.amount())
                .description(request.description())
                .build();
    }

    public static PaymentResponse mapEntityToResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getDescription(),
                payment.getCreatedAt());
    }

}
