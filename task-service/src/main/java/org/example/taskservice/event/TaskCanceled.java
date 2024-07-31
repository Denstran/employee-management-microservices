package org.example.taskservice.event;

import lombok.Data;

@Data
public class TaskCanceled {
    private final String taskOwnerEmail;
    private final String taskDescription;
    private final String taskGiverEmail;
}
