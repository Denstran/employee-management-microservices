package org.example.taskservice.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.taskservice.model.TaskStatus;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskDTO implements Serializable {
    private Long id;
    @NotNull(message = "Описание задачи не может быть пустым!")
    @NotEmpty(message = "Описание задачи не может быть пустым!")
    private String taskDescription;
    private LocalDate taskCreated;
    @NotNull(message = "Срок выполнения задачи не должен отсутствовать!")
    @Future(message = "Срок выполнения задачи должен быть в будущем!")
    private LocalDate taskDeadLine;
    private TaskStatus taskStatus;

    @NotNull(message = "Сотрудник, получающий задачу, не может отсутствовать!")
    @NotEmpty(message = "Email сотрудника, получающего задачу, не может быть пустым!")
    private String taskOwnerEmail;
    @NotNull(message = "Сотрудник, выдающий задачу, не может отсутствовать!")
    @NotEmpty(message = "Email сотрудника, выдавшего задачу, не может быть пустым!")
    private String taskGiverEmail;

    @NotNull(message = "У задачи должен быть выставлен приоритет!")
    private Long priorityId;

    private String ownerContacts;
    private String giverContacts;
}