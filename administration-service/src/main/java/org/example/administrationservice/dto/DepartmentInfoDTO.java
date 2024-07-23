package org.example.administrationservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.administrationservice.model.Money;

@Data
@NoArgsConstructor
public class DepartmentInfoDTO {

    private String departmentName;

    @NotNull(message = "Филиал не должен отсутствовать!")
    private Long companyBranchId;

    @NotNull(message = "Отдел не должен отсутствовать!")
    private Long departmentId;

    @NotNull
    @Valid
    private Money departmentBudget;
}
