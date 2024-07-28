package org.example.paymentlogservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EMPLOYEE_PAYMENT_LOG")
@Getter
@Setter
public class EmployeePaymentLog extends BasePaymentEntity {

    @Column(name = "EMPLOYEE_ID")
    @NotNull(message = "Сотрудник не должен отсутствовать!")
    private Long employeeId;

    public static EmployeePaymentLog createPaymentLog(Long employeeId, Money amount, boolean isPositive) {
        EmployeePaymentLog paymentLog = new EmployeePaymentLog();
        paymentLog.setEmployeeId(employeeId);
        paymentLog.setPaymentType(PaymentType.SALARY_CHANGES);
        paymentLog.setPaymentAmount(amount);
        paymentLog.setTransferAction(isPositive ? TransferAction.INCREASE : TransferAction.DECREASE);

        return paymentLog;
    }
}
