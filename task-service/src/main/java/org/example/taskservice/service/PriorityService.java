package org.example.taskservice.service;

import org.example.taskservice.exception.ResourceNotFoundException;
import org.example.taskservice.model.Priority;
import org.example.taskservice.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriorityService {
    private final PriorityRepository repository;

    @Autowired
    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public boolean existsByTitleCompanyBranchDepartment(String title, Long companyBranchId, Long departmentId) {
        return repository.existsByTitleAndCompanyBranchIdAndDepartmentId(title, companyBranchId, departmentId);
    }

    public Priority getById(Long priorityId) {
        return repository.findById(priorityId).orElseThrow(() ->
                new ResourceNotFoundException("Не удалось найти приоритет с таким id!"));
    }

    public Optional<Priority> getByTitleCompanyBranchDepartment(String title, Long companyBranchId, Long departmentId) {
        return repository.findByTitleAndCompanyBranchIdAndDepartmentId(title, companyBranchId, departmentId);
    }
}
