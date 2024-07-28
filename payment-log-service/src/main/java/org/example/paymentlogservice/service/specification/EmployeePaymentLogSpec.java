package org.example.paymentlogservice.service.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.example.paymentlogservice.dto.EmployeeDTO;
import org.example.paymentlogservice.feign.EmployeeService;
import org.example.paymentlogservice.model.EmployeePaymentLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeePaymentLogSpec {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeePaymentLogSpec(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public static Specification<EmployeePaymentLog> isEqualToEmployeeId(Long employeeId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("employeeId"), employeeId);
    }

    public Specification<EmployeePaymentLog> isEqualToCompanyBranchId(Long companyBranchId) {
        return (root, query, criteriaBuilder) -> {
            List<EmployeeDTO> employeeDTOS = employeeService.getEmployeesFromBranch(companyBranchId);
            List<Long> ids = employeeDTOS.stream().map(EmployeeDTO::getId).toList();
            return root.in(ids);
        };
    }

    public Specification<EmployeePaymentLog> isEqualToCompanyBranchIdAndDepartmentId(Long companyBranchId,
                                                                                            Long departmentId) {
        return ((root, query, criteriaBuilder) -> {
            List<EmployeeDTO> employeeDTOS =
                    employeeService.getEmployeesFromBranchAndDepartment(companyBranchId, departmentId);

            List<Long> ids = employeeDTOS.stream().map(EmployeeDTO::getId).toList();
            Predicate isIn = root.in(ids);
            return criteriaBuilder.and(isIn);
        });
    }

    public Specification<EmployeePaymentLog> setupSpecification(Long employee) {
        return Specification.where(isEqualToEmployeeId(employee));
    }

    public Specification<EmployeePaymentLog> setupSpecification(Long companyBranchId, Long departmentId) {
        return Specification.where(isEqualToCompanyBranchIdAndDepartmentId(companyBranchId, departmentId));
    }

    public Specification<EmployeePaymentLog> setupSpecification(long companyBranchId) {
        return Specification.where(isEqualToCompanyBranchId(companyBranchId));
    }
}
