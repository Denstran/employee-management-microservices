package org.example.paymentlogservice.event.employeePaymentLog;

import lombok.Data;
import org.example.paymentlogservice.model.Money;
import org.example.paymentlogservice.model.PaymentType;
import org.example.paymentlogservice.model.TransferAction;

import java.util.Date;

@Data
public class EmployeePaymentCreated {
    private final Long employeeId;
    private final PaymentType paymentType;
    private final Money paymentAmount;
    private final Date dateOfPayment;
    private final TransferAction transferAction;
}
