package org.example.taskservice.event;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskFinished {
    private final String giverEmail;
    private final String ownerEmail;
    private final LocalDate finishDate;
    private final String taskDescription;
}