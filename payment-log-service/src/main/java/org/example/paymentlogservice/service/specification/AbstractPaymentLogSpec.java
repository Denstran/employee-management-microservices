package org.example.paymentlogservice.service.specification;

import jakarta.persistence.criteria.Predicate;
import org.example.paymentlogservice.model.BasePaymentEntity;
import org.example.paymentlogservice.model.TransferAction;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractPaymentLogSpec {
    public static <T extends BasePaymentEntity> Specification<T> isBetweenDates(String startDate, String endDate) {
        return ((root, query, criteriaBuilder) -> {
            Date start;
            Date end;
            try {
                start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            Predicate greaterThanOrEqualToStartDate =
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dateOfPayment"), start);
            Predicate lessThanOrEqualToEndDate =
                    criteriaBuilder.lessThanOrEqualTo(root.get("dateOfPayment"), end);

            return criteriaBuilder.and(greaterThanOrEqualToStartDate, lessThanOrEqualToEndDate);
        });
    }

    public static <T extends BasePaymentEntity> Specification<T> isTransferActionEqualTo(String transferAction) {
        TransferAction transferActionEnum = TransferAction.valueOf(transferAction);
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("transferAction"), transferActionEnum));
    }
}
