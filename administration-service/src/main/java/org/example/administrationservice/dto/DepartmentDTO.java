package org.example.administrationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.administrationservice.model.department.DepartmentType;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentDTO {
    private Long id;

    @NotNull
    @Size(min = 1, max = 255, message = "Название отдела должно быть от 1 до 255 символов!")
    @NotBlank(message = "Название отдела не может быть пустым!")
    private String departmentName;

    @NotNull(message = "Отдел должен иметь тип!")
    private DepartmentType departmentType;
}
