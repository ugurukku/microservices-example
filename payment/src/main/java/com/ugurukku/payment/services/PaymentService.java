package com.ugurukku.payment.services;

import com.ugurukku.payment.client.CountryClient;
import com.ugurukku.payment.entities.Payment;
import com.ugurukku.payment.exceptions.NotFoundException;
import com.ugurukku.payment.mappers.PaymentMapper;
import com.ugurukku.payment.models.request.PaymentRequest;
import com.ugurukku.payment.models.response.PaymentResponse;
import com.ugurukku.payment.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

import static com.ugurukku.payment.mappers.PaymentMapper.mapEntityToResponse;
import static com.ugurukku.payment.mappers.PaymentMapper.mapRequestToEntity;
import static com.ugurukku.payment.models.constant.ExceptionConstants.COUNTRY_NOT_FOUND_CODE;
import static com.ugurukku.payment.models.constant.ExceptionConstants.COUNTRY_NOT_FOUND_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;

    private final CountryClient countryClient;

    public PaymentResponse getPaymentById(Long id) {
        log.info("getPaymentById.start id: {}", id);
        return mapEntityToResponse(fetchPaymentIfExist(id));
    }

    public List<PaymentResponse> getAllPayments() {
        log.info("getAllPayments.start");
        return repository.findAll().stream().map(PaymentMapper::mapEntityToResponse).toList();
    }

    public void savePayment(PaymentRequest request) {
        log.info("savePayment.started");
        countryClient.getAllAvailableCountries(request.currency())
                .stream()
                .filter(country -> country.remainingLimit().compareTo(request.amount()) > 0)
                .findFirst()
                .orElseThrow(
                        () ->
                                new NotFoundException(String.format(
                                        COUNTRY_NOT_FOUND_MESSAGE,
                                        request.amount(),
                                        request.currency()), COUNTRY_NOT_FOUND_CODE));

        repository.save(mapRequestToEntity(request));
        log.info("savePayment.success");

    }

    public void updatePaymentById(Long id, PaymentRequest request) {
        log.info("updatePaymentById.started id: {}", id);
        Payment payment = fetchPaymentIfExist(id);
        payment.setDescription(request.description());
        payment.setAmount(request.amount());
        repository.save(payment);
        log.info("updatePaymentById.success id: {}", id);

    }

    public void removePaymentById(Long id) {
        log.info("removePaymentById.started id: {}", id);
        fetchPaymentIfExist(id);
        repository.deleteById(id);
        log.info("removePaymentById.success id: {}", id);

    }

    private Payment fetchPaymentIfExist(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }


}
