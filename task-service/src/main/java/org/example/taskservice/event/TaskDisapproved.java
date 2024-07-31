package org.example.taskservice.event;

import lombok.Data;

@Data
public class TaskDisapproved {
    private final String ownerEmail;
    private final String taskDescription;
    private final String giverEmail;
}
