package org.example.paymentlogservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.paymentlogservice.event.departmentInfo.DepartmentInfoBudgetAdjusted;
import org.example.paymentlogservice.event.departmentInfo.DepartmentInfoBudgetReduced;
import org.example.paymentlogservice.event.departmentInfo.DepartmentInfoRegistered;

@Entity
@Table(name = "DEPARTMENT_INFO_PAYMENT_LOG")
@Getter
@Setter
public class DepartmentInfoPaymentLog extends BasePaymentEntity {

    @Column(name = "COMPANY_BRANCH_ID")
    @NotNull(message = "Филиал не должен отсутствовать!")
    private Long companyBranchId;

    @Column(name = "DEPARTMENT_ID")
    @NotNull(message = "Отдел не должен отсутствовать!")
    private Long departmentId;

    public static DepartmentInfoPaymentLog createPaymentLog(Long companyBranchId,
                                                            Long departmentId,
                                                            Money amount,
                                                            boolean isPositive) {
        DepartmentInfoPaymentLog departmentInfoPaymentLog = new DepartmentInfoPaymentLog();
        departmentInfoPaymentLog.setCompanyBranchId(companyBranchId);
        departmentInfoPaymentLog.setDepartmentId(departmentId);
        departmentInfoPaymentLog.setPaymentType(PaymentType.BUDGET_CHANGES);
        departmentInfoPaymentLog
                .setPaymentAmount(amount);
        departmentInfoPaymentLog.setTransferAction(isPositive ? TransferAction.INCREASE : TransferAction.DECREASE);
        return departmentInfoPaymentLog;
    }

    public static DepartmentInfoPaymentLog createPaymentLog(DepartmentInfoBudgetAdjusted event) {
        return createPaymentLog(
                event.getCompanyBranchId(),
                event.getDepartmentId(),
                new Money(event.getAdjustmentAmount().getAmount()),
                true);
    }

    public static DepartmentInfoPaymentLog createPaymentLog(DepartmentInfoBudgetReduced event) {
        return createPaymentLog(
                event.getCompanyBranchId(),
                event.getDepartmentId(),
                new Money(event.getReduceAmount().getAmount()),
                false
        );
    }

    public static DepartmentInfoPaymentLog createPaymentLog(DepartmentInfoRegistered event) {
        return createPaymentLog(
                event.getCompanyBranchId(),
                event.getDepartmentId(),
                new Money(event.getDepartmentBudget().getAmount()),
                true);
    }
}
