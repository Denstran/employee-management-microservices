package org.example.paymentlogservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
        companyBranchPaymentLog
                .setPaymentAmount(amount);
        companyBranchPaymentLog.setTransferAction(isPositive ? TransferAction.INCREASE : TransferAction.DECREASE);
        return  companyBranchPaymentLog;
    }
}
