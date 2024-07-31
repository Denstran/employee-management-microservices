package org.example.taskservice.event;

import lombok.Data;

@Data
public class TaskApproved {
    private final String ownerEmail;
    private final String taskDescription;
}
