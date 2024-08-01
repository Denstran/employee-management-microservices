package org.example.taskservice.event;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDeadlineExtended {
    private final String ownerEmail;
    private final String taskDescription;
    private final String giverEmail;
    private final LocalDate extendedDeadline;
}