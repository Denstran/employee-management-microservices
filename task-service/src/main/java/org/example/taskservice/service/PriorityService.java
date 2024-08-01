package org.example.taskservice.service;

import org.example.taskservice.exception.ResourceNotFoundException;
import org.example.taskservice.model.Priority;
import org.example.taskservice.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriorityService {
    private final PriorityRepository repository;

    @Autowired
    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public Priority getById(Long priorityId) {
        return repository.findById(priorityId).orElseThrow(() ->
                new ResourceNotFoundException("Не удалось найти приоритет с таким id!"));
    }
}
