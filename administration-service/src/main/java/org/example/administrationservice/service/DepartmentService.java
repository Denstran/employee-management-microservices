package org.example.administrationservice.service;

import org.example.administrationservice.model.department.Department;
import org.example.administrationservice.model.department.DepartmentType;
import org.example.administrationservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository repository;

    @Autowired
    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public boolean existsByName(String departmentName) {
        return repository.existsByDepartmentName(departmentName);
    }

    public boolean existsByType(DepartmentType departmentType) {
        return repository.existsByDepartmentType(departmentType);
    }

    public Optional<Department> getByName(String departmentName) {
        return repository.findDepartmentByDepartmentName(departmentName);
    }
}
