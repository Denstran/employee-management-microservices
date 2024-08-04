package org.example.taskservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "PRIORITY", uniqueConstraints = {
        @UniqueConstraint(name = "CompanyBranchDepartmentUniqueConstraint", columnNames = {
                "COMPANY_BRANCH_ID", "DEPARTMENT_ID"})
})
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Priority {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", unique = true)
    @NotNull(message = "Название приоритета не может быть пустым!")
    @NotEmpty(message = "Название приоритета не может быть пустым!")
    private String title;

    @NotNull(message = "Цвет приоритета не может быть пустым!")
    @NotEmpty(message = "Цвет приоритета не может быть пустым!")
    private String color;

    @Column(name = "COMPANY_BRANCH_ID", updatable = false)
    private Long companyBranchId;
    @Column(name = "DEPARTMENT_ID", updatable = false)
    private Long departmentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Priority priority)) return false;
        return Objects.equals(id, priority.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
