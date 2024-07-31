package org.example.taskservice.service;

import org.example.taskservice.model.Task;
import org.example.taskservice.model.TaskStatus;
import org.example.taskservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {
    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createTask(Task task) {
        if (task == null)
            throw new NullPointerException("Передан не корректные аргументы");

        task.createTask();
        repository.save(task);
    }

    @Transactional
    public Task finishTask(Task task) {
        if (task == null)
            throw new NullPointerException("Передан не корректные аргументы");
        if (!task.getTaskStatus().equals(TaskStatus.IN_PROCESS))
            return task;

        task.finishTask();
        return repository.save(task);
    }

    @Transactional
    public Task approveTask(Task task) {
        if (task == null)
            throw new NullPointerException("Передан не корректные аргументы");
        if (!task.getTaskStatus().equals(TaskStatus.ON_VALIDATION))
            return task;

        task.approve();
        return repository.save(task);
    }

    @Transactional
    public Task cancelTask(Task task) {
        if (task == null)
            throw new NullPointerException("Передан не корректные аргументы");
        if (!task.getTaskStatus().equals(TaskStatus.IN_PROCESS))
            return task;

        task.cancel();
        return repository.save(task);
    }

    @Transactional
    public Task extendTaskDeadline(Task task) {
        if (task == null)
            throw new NullPointerException("Передан не корректные аргументы");

        if (!task.getTaskStatus().equals(TaskStatus.IN_PROCESS))
            return task;

        task.extendTaskDeadline();
        return repository.save(task);
    }

    @Transactional
    public Task disapproveTask(Task task) {
        if (task == null)
            throw new NullPointerException("Передан не корректные аргументы");
        if (!task.getTaskStatus().equals(TaskStatus.ON_VALIDATION))
            return task;

        task.disapprove();
        return repository.save(task);
    }
}
