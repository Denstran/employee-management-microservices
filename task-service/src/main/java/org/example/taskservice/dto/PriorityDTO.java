package org.example.taskservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PriorityDTO {
    private Long id;

    @NotNull(message = "Название приоритета не может быть пустым!")
    @NotEmpty(message = "Название приоритета не может быть пустым!")
    private String title;
    @NotNull(message = "Цвет приоритета не может быть пустым!")
    @NotEmpty(message = "Цвет приоритета не может быть пустым!")
    private String color;

    @NotNull(message = "Филиал не должен быть пустым!")
    private Long companyBranchId;
    @NotNull(message = "Отдел не должен быть пустым!")
    private Long departmentId;


}
