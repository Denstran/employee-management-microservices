package org.example.paymentlogservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.paymentlogservice.event.companyBranch.CompanyBranchBudgetAdjusted;
import org.example.paymentlogservice.event.companyBranch.CompanyBranchBudgetReduced;
import org.example.paymentlogservice.event.companyBranch.CompanyBranchCreated;

@Entity
@Table(name = "COMPANY_BRANCH_PAYMENT_LOG")
@Getter
@Setter
public class CompanyBranchPaymentLog extends BasePaymentEntity {

    @Column(name = "COMPANY_BRANCH_ID")
    @NotNull(message = "Филиал не должен отсутствовать!")
    private Long companyBranchId;

    public static CompanyBranchPaymentLog createPaymentLog(Long companyBranchId,
                                                           Money amount,
                                                           boolean isPositive) {
        CompanyBranchPaymentLog companyBranchPaymentLog = new CompanyBranchPaymentLog();
        companyBranchPaymentLog.setCompanyBranchId(companyBranchId);
        companyBranchPaymentLog.setPaymentType(PaymentType.BUDGET_CHANGES);
        companyBranchPaymentLog.setPaymentAmount(amount);
        companyBranchPaymentLog.setTransferAction(isPositive ? TransferAction.INCREASE : TransferAction.DECREASE);
        return companyBranchPaymentLog;
    }

    public static CompanyBranchPaymentLog createPaymentLog(CompanyBranchCreated event) {
        return createPaymentLog(
                event.getCompanyBranchId(),
                new Money(event.getBudget().getAmount()),
                true);
    }


    public static CompanyBranchPaymentLog createPaymentLog(CompanyBranchBudgetAdjusted event) {
        return createPaymentLog(
                event.getCompanyBranchId(),
                new Money(event.getAdjustmentAmount().getAmount()),
                true);
    }

    public static CompanyBranchPaymentLog createPaymentLog(CompanyBranchBudgetReduced event) {
        return createPaymentLog(
                event.getCompanyBranchId(),
                new Money(event.getReducedAmount().getAmount()),
                false);
    }
}
