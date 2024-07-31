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
@Table(name = "PRIORITY")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Priority {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    @NotNull(message = "Название приоритета не может быть пустым!")
    @NotEmpty(message = "Название приоритета не может быть пустым!")
    private String title;

    @NotNull(message = "Цвет приоритета не может быть пустым!")
    @NotEmpty(message = "Цвет приоритета не может быть пустым!")
    private String color;

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
