package org.example.paymentlogservice.service;

import org.example.dates.DateUtils;
import org.example.paymentlogservice.dto.EmployeeDTO;
import org.example.paymentlogservice.feign.EmployeeService;
import org.example.paymentlogservice.model.EmployeePaymentLog;
import org.example.paymentlogservice.model.Money;
import org.example.paymentlogservice.model.PaymentType;
import org.example.paymentlogservice.repository.EmployeePaymentLogRepository;
import org.example.paymentlogservice.service.specification.EmployeePaymentLogSpec;
import org.example.paymentlogservice.service.specification.PaymentSpecificationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeePaymentLogService {
    private final EmployeePaymentLogRepository repository;
    private final EmployeePaymentLogSpec employeePaymentLogSpec;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeePaymentLogService(EmployeePaymentLogRepository repository,
                                     EmployeePaymentLogSpec employeePaymentLogSpec,
                                     EmployeeService employeeService) {
        this.repository = repository;
        this.employeePaymentLogSpec = employeePaymentLogSpec;
        this.employeeService = employeeService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveEmployeePaymentLog(EmployeePaymentLog employeePaymentLog) {
        repository.save(employeePaymentLog);
    }

    public List<EmployeePaymentLog> getEmployeePaymentLog(Long employeeId, String startDate, String endDate,
                                                          String transferAction) {
        validateResources(employeeId);
        Specification<EmployeePaymentLog> spec = employeePaymentLogSpec.setupSpecification(employeeId);
        spec = PaymentSpecificationProcessor.processDateParams(startDate, endDate, spec);
        spec = PaymentSpecificationProcessor.processAction(transferAction, spec);

        return repository.findAll(spec);
    }

    public List<EmployeePaymentLog> getAllEmployeesPaymentLogsFromCompanyBranch(Long companyBranchId,
                                                                                Long departmentId,
                                                                                String startDate,
                                                                                String endDate,
                                                                                String transferAction) {
        validateResources(companyBranchId);
        Specification<EmployeePaymentLog> spec = setupSpec(companyBranchId, departmentId);
        spec = PaymentSpecificationProcessor.processDateParams(startDate, endDate, spec);
        spec = PaymentSpecificationProcessor.processAction(transferAction, spec);

        return repository.findAll(spec);
    }

    private Specification<EmployeePaymentLog> setupSpec(Long companyBranchId, Long departmentId) {
        if (departmentId == null)
            return employeePaymentLogSpec.setupSpecification((long) companyBranchId);

        return employeePaymentLogSpec.setupSpecification(companyBranchId, departmentId);
    }

    private void validateResources(Long...resources) {
        for (Long resource : resources)
            checkResourceValidity(resource);
    }

    private void checkResourceValidity(Long resource) {
        if (resource == null || resource <= 0)
            throw new IllegalArgumentException("Выбран не существующий ресурс!");
    }

    public Money getEmployeeAverageYearSalary(Long employeeId, LocalDate endOfBillingPeriod) {
        EmployeeDTO employee = employeeService.getEmployeeById(employeeId);
        LocalDate beginning = getBeginningOfBillingPeriod(employee, endOfBillingPeriod);

        List<EmployeePaymentLog> paymentLogs =
                getEmployeePaymentLogsBetweenDatesAndPaymentType(
                        beginning, endOfBillingPeriod, employee.getId(), List.of(PaymentType.SALARY, PaymentType.BONUS));

        double result = countBillingResult(paymentLogs);
        return new Money(result);
    }

    private double countBillingResult(List<EmployeePaymentLog> paymentLogs) {
        return paymentLogs.stream()
                .mapToDouble(paymentLog -> paymentLog.getPaymentAmount().getAmount())
                .sum();
    }

    private LocalDate getBeginningOfBillingPeriod(EmployeeDTO employee, LocalDate endOfBillingPeriod) {
        LocalDate beginning;
        LocalDate employmentDate = DateUtils.asLocalDate(employee.getEmploymentDate());

        if (employmentDate.getYear() == endOfBillingPeriod.getYear())
            beginning = employmentDate;
        else
            beginning = endOfBillingPeriod.minusYears(1L);

        return beginning;
    }

    public List<EmployeePaymentLog> getEmployeePaymentLogsBetweenDatesAndPaymentType(LocalDate beginning,
                                                                                     LocalDate end,
                                                                                     Long employeeId,
                                                                                     List<PaymentType> paymentTypes) {
        return repository.findByDateOfPaymentBetweenAndEmployeeIdAndPaymentTypeIn(
                DateUtils.asDate(beginning), DateUtils.asDate(end), employeeId, paymentTypes);
    }

}
