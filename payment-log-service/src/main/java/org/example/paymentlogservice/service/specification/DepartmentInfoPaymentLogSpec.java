package org.example.paymentlogservice.service.specification;

import jakarta.persistence.criteria.Predicate;
import org.example.paymentlogservice.model.DepartmentInfoPaymentLog;
import org.springframework.data.jpa.domain.Specification;

public class DepartmentInfoPaymentLogSpec {

    public static Specification<DepartmentInfoPaymentLog> isEqualToCompanyBranchIdAndDepartmentId(Long companyBranchId,
                                                                                                  Long departmentId) {
        return ((root, query, criteriaBuilder) -> {
            Predicate isEqualToDepartmentId = criteriaBuilder.equal(root.get("departmentId"), departmentId);
            Predicate isEqualToCompanyBranchId = criteriaBuilder.equal(root.get("companyBranchId"), companyBranchId);

            return criteriaBuilder.and(isEqualToCompanyBranchId, isEqualToDepartmentId);
        });
    }

    public static Specification<DepartmentInfoPaymentLog> isEqualToCompanyBranchId(Long companyBranchId) {
        return ((root, query, criteriaBuilder) -> {
            Predicate isEqualToCompanyBranchId = criteriaBuilder.equal(root.get("companyBranchId"), companyBranchId);

            return criteriaBuilder.and(isEqualToCompanyBranchId);
        });
    }

    public static Specification<DepartmentInfoPaymentLog> setupSpecification(Long companyBranchId) {
        return Specification.where(isEqualToCompanyBranchId(companyBranchId));
    }

    public static Specification<DepartmentInfoPaymentLog> setupSpecification(Long companyBranchId, Long departmentId) {
        return Specification.where(isEqualToCompanyBranchIdAndDepartmentId(companyBranchId, departmentId));
    }
}
