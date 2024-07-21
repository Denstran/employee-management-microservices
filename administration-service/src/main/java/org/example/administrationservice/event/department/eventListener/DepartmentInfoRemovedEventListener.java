package org.example.administrationservice.event.department.eventListener;

import org.example.administrationservice.event.department.DepartmentInfoKafkaEventPublisher;
import org.example.administrationservice.event.department.DepartmentInfoRemoved;
import org.example.administrationservice.model.companyBranch.CompanyBranch;
import org.example.administrationservice.service.CompanyBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class DepartmentInfoRemovedEventListener {
    private final CompanyBranchService companyBranchService;
    private final DepartmentInfoKafkaEventPublisher kafkaEventPublisher;

    @Autowired
    public DepartmentInfoRemovedEventListener(CompanyBranchService companyBranchService,
                                              DepartmentInfoKafkaEventPublisher kafkaEventPublisher) {
        this.companyBranchService = companyBranchService;
        this.kafkaEventPublisher = kafkaEventPublisher;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void departmentInfoBudgetReducedEventHandler(DepartmentInfoRemoved event) {
        CompanyBranch companyBranch = companyBranchService.getById(event.getCompanyBranchId());
        companyBranchService.adjustBudget(companyBranch, event.getDepartmentInfoBudget());
        kafkaEventPublisher.publishDepartmentInfoRemovedEvent(event);
    }
}
