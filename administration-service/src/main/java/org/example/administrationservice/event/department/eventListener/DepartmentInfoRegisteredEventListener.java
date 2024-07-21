package org.example.administrationservice.event.department.eventListener;

import org.example.administrationservice.event.department.DepartmentInfoKafkaEventPublisher;
import org.example.administrationservice.event.department.DepartmentInfoRegistered;
import org.example.administrationservice.model.companyBranch.CompanyBranch;
import org.example.administrationservice.service.CompanyBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class DepartmentInfoRegisteredEventListener {
    private final CompanyBranchService companyBranchService;
    private final DepartmentInfoKafkaEventPublisher kafkaEventPublisher;

    @Autowired
    public DepartmentInfoRegisteredEventListener(CompanyBranchService companyBranchService,
                                                 DepartmentInfoKafkaEventPublisher kafkaEventPublisher) {
        this.companyBranchService = companyBranchService;
        this.kafkaEventPublisher = kafkaEventPublisher;
    }

    @TransactionalEventListener
    public void departmentInfoRegisteredEventHandler(DepartmentInfoRegistered departmentInfoRegistered) {
        processCompanyBranchBudgetChanges(departmentInfoRegistered);
        processDepartmentInfoBudgetChanges(departmentInfoRegistered);
    }

    private void processDepartmentInfoBudgetChanges(DepartmentInfoRegistered departmentInfoRegistered) {
        kafkaEventPublisher.publishDepartmentInfoRegisteredEvent(departmentInfoRegistered);
    }

    private void processCompanyBranchBudgetChanges(DepartmentInfoRegistered departmentInfoRegistered) {
        CompanyBranch companyBranch = companyBranchService.getById(departmentInfoRegistered.getCompanyBranchId());
        companyBranchService.reduceBudget(companyBranch, departmentInfoRegistered.getDepartmentBudget());
    }
}
