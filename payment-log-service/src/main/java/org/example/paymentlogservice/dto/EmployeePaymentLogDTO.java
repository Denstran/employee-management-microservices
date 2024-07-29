package org.example.paymentlogservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.paymentlogservice.model.Money;
import org.example.paymentlogservice.model.PaymentType;
import org.example.paymentlogservice.model.TransferAction;

import java.util.Date;

@Getter
@Setter
public class EmployeePaymentLogDTO extends BasePaymentEntityDTO {
    private Long employeeId;

    public EmployeePaymentLogDTO(Long id,
                                 PaymentType paymentType,
                                 Money paymentAmount,
                                 Date dateOfPayment,
                                 TransferAction transferAction,
                                 Long employeeId) {
        super(id, paymentType, paymentAmount, dateOfPayment, transferAction);
        this.employeeId = employeeId;
    }
}
