package org.example.paymentlogservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.paymentlogservice.model.Money;
import org.example.paymentlogservice.model.PaymentType;
import org.example.paymentlogservice.model.TransferAction;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CompanyBranchPaymentLogDTO extends BasePaymentEntityDTO {
    @NotNull(message = "Филиал не должен отсутствовать!")
    private Long companyBranchId;

    public CompanyBranchPaymentLogDTO(Long id,
                                      PaymentType paymentType,
                                      Money paymentAmount,
                                      Date dateOfPayment,
                                      TransferAction transferAction,
                                      Long companyBranchId) {
        super(id, paymentType, paymentAmount, dateOfPayment, transferAction);
        this.companyBranchId = companyBranchId;
    }
}
