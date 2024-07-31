package org.example.taskservice.event;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskCreated {
    private final String employeeEmail;
    private final String taskDescription;
    private final String giverEmail;
    private final LocalDate taskDeadLine;
}
