package com.ugurukku.payment.models.response;

import java.util.List;

public record PageablePaymentResponse(List<PaymentResponse> paymentResponses,
                                      Long totalElements,
                                      int totalPages,
                                      boolean hasNextPage) {




}
