package com.ugurukku.payment.controllers;

import com.ugurukku.payment.models.request.PaymentRequest;
import com.ugurukku.payment.models.response.PaymentResponse;
import com.ugurukku.payment.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @GetMapping
    public List<PaymentResponse> getAll() {
        return service.getAllPayments();
    }

    @GetMapping("/{id}")
    public PaymentResponse getById(@PathVariable(name = "id") Long id) {
        return service.getPaymentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPayment(@RequestBody PaymentRequest request) {
        service.savePayment(request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        service.removePaymentById(id);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable(name = "id") Long id, @RequestBody PaymentRequest request) {
        service.updatePaymentById(id, request);
    }


}
