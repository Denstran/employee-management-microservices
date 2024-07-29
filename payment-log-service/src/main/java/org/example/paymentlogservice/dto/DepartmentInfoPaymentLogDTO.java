package org.example.paymentlogservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.paymentlogservice.model.Money;
import org.example.paymentlogservice.model.PaymentType;
import org.example.paymentlogservice.model.TransferAction;

import java.util.Date;

@Getter
@Setter
public class DepartmentInfoPaymentLogDTO extends BasePaymentEntityDTO {
    @NotNull(message = "Филиал не должен отсутствовать!")
    private Long companyBranchId;
    @NotNull(message = "Отдел не должен отсутствовать!")
    private Long departmentId;

    public DepartmentInfoPaymentLogDTO(Long id,
                                       PaymentType paymentType,
                                       Money paymentAmount,
                                       Date dateOfPayment,
                                       TransferAction transferAction,
                                       Long companyBranchId,
                                       Long departmentId) {
        super(id, paymentType, paymentAmount, dateOfPayment, transferAction);
        this.companyBranchId = companyBranchId;
        this.departmentId = departmentId;
    }
}
