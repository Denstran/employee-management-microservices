package org.example.administrationservice.event.department.eventListener;

import org.example.administrationservice.event.department.DepartmentInfoBudgetAdjusted;
import org.example.administrationservice.event.department.DepartmentInfoKafkaEventPublisher;
import org.example.administrationservice.model.companyBranch.CompanyBranch;
import org.example.administrationservice.service.CompanyBranchService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class DepartmentInfoBudgetAdjustedEventListener {
    private final CompanyBranchService companyBranchService;
    private final DepartmentInfoKafkaEventPublisher kafkaEventPublisher;

    public DepartmentInfoBudgetAdjustedEventListener(CompanyBranchService companyBranchService,
                                                     DepartmentInfoKafkaEventPublisher kafkaEventPublisher) {
        this.companyBranchService = companyBranchService;
        this.kafkaEventPublisher = kafkaEventPublisher;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void departmentInfoBudgetAdjustedEventHandler(DepartmentInfoBudgetAdjusted event) {
        CompanyBranch companyBranch = companyBranchService.getById(event.getCompanyBranchId());
        companyBranchService.reduceBudget(companyBranch, event.getAdjustmentAmount());
        kafkaEventPublisher.publishDepartmentInfoBudgetAdjustedEvent(event);
    }
}
