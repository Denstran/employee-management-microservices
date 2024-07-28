package org.example.paymentlogservice.service.specification;

import org.example.paymentlogservice.model.CompanyBranchPaymentLog;
import org.springframework.data.jpa.domain.Specification;

public class CompanyBranchPaymentLogSpec {

    public static Specification<CompanyBranchPaymentLog> isIdEqual(Long companyBranchId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("companyBranchId"), companyBranchId);
    }

    public static Specification<CompanyBranchPaymentLog> setupSpecification(Long companyBranchId) {
        return Specification.where(isIdEqual(companyBranchId));
    }
}
