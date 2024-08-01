package org.example.taskservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.taskservice.event.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDate;

@Entity
@Table(name = "TASK")
@Getter
@Setter
public class Task extends AbstractAggregateRoot<Task> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TASK_DESCRIPTION")
    @NotNull(message = "Описание задачи не может быть пустым!")
    @NotEmpty(message = "Описание задачи не может быть пустым!")
    private String taskDescription;

    @Column(name = "TASK_CREATED", updatable = false)
    @Temporal(value = TemporalType.DATE)
    @NotNull(message = "Дата выдачи задачи не может отсутствовать!")
    private LocalDate taskCreated;

    @Column(name = "TASK_DEADLINE")
    @Temporal(value = TemporalType.DATE)
    @Future(message = "Срок выполнения задачи должен быть в будущем!")
    @NotNull(message = "Срок выполнения задачи не должен отсутствовать!")
    private LocalDate taskDeadLine;

    @Column(name = "TASK_STATUS")
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Статус задачи не может отсутствовать!")
    private TaskStatus taskStatus;

    @Column(name = "TASK_OWNER_EMAIL")
    private String taskOwnerEmail;

    @Column(name = "TASK_GIVER_EMAIL")
    private String taskGiverEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRIORITY_ID", foreignKey = @ForeignKey(name = "TASK_PRIORITY_FK"))
    @NotNull(message = "У задачи не может отсутствовать приоритет!")
    private Priority priority;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskCreated=" + taskCreated +
                ", taskDeadLine=" + taskDeadLine +
                ", taskStatus=" + taskStatus +
                ", taskOwner=" + taskOwnerEmail +
                ", taskGiver=" + taskGiverEmail +
                '}';
    }

    public void createTask() {
        taskCreated = LocalDate.now();
        taskStatus = TaskStatus.IN_PROCESS;
        registerEvent(new TaskCreated(
                getTaskOwnerEmail(),
                taskDescription,
                getTaskGiverEmail(),
                taskDeadLine));
    }

    public void finishTask() {
        taskStatus = TaskStatus.ON_VALIDATION;
        registerEvent(new TaskFinished(
                taskGiverEmail,
                taskOwnerEmail,
                LocalDate.now(),
                taskDescription));
    }

    public void approve() {
        taskStatus = TaskStatus.FINISHED;
        registerEvent(new TaskApproved(taskOwnerEmail, taskDescription));
    }

    public void cancel() {
        taskStatus = TaskStatus.CANCELED;
        registerEvent(new TaskCanceled(
                taskOwnerEmail,
                taskDescription,
                taskGiverEmail));
    }

    public void disapprove() {
        taskStatus = TaskStatus.IN_PROCESS;
        registerEvent(new TaskDisapproved(
                taskOwnerEmail,
                taskDescription,
                taskGiverEmail
        ));
    }

    public void extendTaskDeadline() {
        registerEvent(new TaskDeadlineExtended(
                taskOwnerEmail,
                taskDescription,
                taskGiverEmail,
                taskDeadLine
        ));
    }
}
