package org.example.paymentlogservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.paymentlogservice.model.Money;
import org.example.paymentlogservice.model.PaymentType;
import org.example.paymentlogservice.model.TransferAction;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BasePaymentEntityDTO {
    private Long id;
    @NotNull(message = "Тип платежа не может быть пустым!")
    private PaymentType paymentType;
    @Valid
    private Money paymentAmount;
    @FutureOrPresent(message = "Дата платежа не может быть в прошлом!")
    private Date dateOfPayment;
    @NotNull
    private TransferAction transferAction;
}
