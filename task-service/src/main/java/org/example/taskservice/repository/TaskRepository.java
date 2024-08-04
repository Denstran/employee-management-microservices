package org.example.taskservice.repository;

import org.example.taskservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskOwnerEmail(String ownerEmail);
    List<Task> findByTaskGiverEmail(String ownerEmail);
}
