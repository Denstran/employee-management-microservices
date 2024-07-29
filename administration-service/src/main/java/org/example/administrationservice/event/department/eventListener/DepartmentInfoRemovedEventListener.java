package org.example.administrationservice.event.department.eventListener;

import lombok.Getter;
import lombok.Setter;
import org.example.administrationservice.dto.EmployeeDTO;
import org.example.administrationservice.event.department.DepartmentInfoKafkaEventPublisher;
import org.example.administrationservice.event.department.DepartmentInfoRemoved;
import org.example.administrationservice.feign.EmployeeService;
import org.example.administrationservice.model.Money;
import org.example.administrationservice.model.companyBranch.CompanyBranch;
import org.example.administrationservice.service.CompanyBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentInfoRemovedEventListener {
    private final CompanyBranchService companyBranchService;
    private final DepartmentInfoKafkaEventPublisher kafkaEventPublisher;
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentInfoRemovedEventListener(CompanyBranchService companyBranchService,
                                              DepartmentInfoKafkaEventPublisher kafkaEventPublisher,
                                              EmployeeService employeeService) {
        this.companyBranchService = companyBranchService;
        this.kafkaEventPublisher = kafkaEventPublisher;
        this.employeeService = employeeService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void departmentInfoBudgetReducedEventHandler(DepartmentInfoRemoved event) {
        CompanyBranch companyBranch = companyBranchService.getById(event.getCompanyBranchId());
        IdsMoneyTuple tuple = getEmployeeSalariesAndIds(event);
        Money adjustmentAmount = Money.sum(tuple.getTotalSalaries(), event.getDepartmentInfoBudget());
        companyBranchService.adjustBudget(companyBranch, adjustmentAmount);
        List<Long> employeesIds = tuple.getEmployeeIds();

        kafkaEventPublisher.publishDepartmentInfoRemovedEvent(employeesIds);
    }

    private IdsMoneyTuple getEmployeeSalariesAndIds(DepartmentInfoRemoved event) {
        IdsMoneyTuple idsMoneyTuple = new IdsMoneyTuple();
        List<EmployeeDTO> employeeDTOS =
                employeeService.getEmployeesFromCompanyBranchAndDepartment(
                        event.getCompanyBranchId(),
                        event.getDepartmentId());

        Money totalResult = new Money(0.0);
        for (EmployeeDTO employee : employeeDTOS) {
            totalResult = Money.sum(totalResult, employee.getSalary());
            idsMoneyTuple.addId(employee.getId());
        }

        idsMoneyTuple.setTotalSalaries(totalResult);
        return idsMoneyTuple;
    }

    @Getter
    @Setter
    private class IdsMoneyTuple {
        private List<Long> employeeIds = new ArrayList<>();
        private Money totalSalaries;

        private void addId(Long id) {
            this.employeeIds.add(id);
        }
    }
}
