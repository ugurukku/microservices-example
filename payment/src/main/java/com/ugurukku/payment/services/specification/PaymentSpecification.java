package com.ugurukku.payment.services.specification;

import com.ugurukku.payment.entities.Payment;
import com.ugurukku.payment.models.request.PaymentCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class PaymentSpecification implements Specification<Payment> {

    private final PaymentCriteria paymentCriteria;
    private static final String AMOUNT = "amount";
    private static final String DESCRIPTION = "DESCRIPTION";

    @Override
    public Predicate toPredicate(Root<Payment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (paymentCriteria != null){
            if (paymentCriteria.amountFrom() != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(AMOUNT),paymentCriteria.amountFrom()));
            }

            if (paymentCriteria.amountTo() != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(AMOUNT),paymentCriteria.amountTo()));
            }

            if (hasText(paymentCriteria.description())){
                predicates.add(criteriaBuilder.like(root.get(DESCRIPTION),"%"+paymentCriteria.description()+"%"));
            }


        }
        return  criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }
}
