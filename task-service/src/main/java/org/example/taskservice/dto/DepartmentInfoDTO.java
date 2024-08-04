package org.example.taskservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentInfoDTO {
    @NotNull(message = "Филиал не должен отсутствовать!")
    private Long companyBranchId;
    @NotNull(message = "Отдел не должен отсутствовать!")
    private Long departmentId;
}
