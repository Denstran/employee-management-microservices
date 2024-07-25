package org.example.administrationservice.service;

import org.example.administrationservice.exception.ResourceNotFoundException;
import org.example.administrationservice.model.department.Department;
import org.example.administrationservice.model.department.DepartmentType;
import org.example.administrationservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository repository;

    @Autowired
    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void create(Department department) {
        repository.save(department);
    }

    @Transactional
    public void update(Department department) {
        repository.save(department);
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

    public Department getReference(Long departmentId) {
        if (departmentId == null || departmentId < 1)
            throw new IllegalArgumentException("Выбран несуществующий отдел!");
        return repository.getReferenceById(departmentId);
    }

    public Department getById(Long departmentId) {

        return repository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Отдел с таким id не существует!"));
    }

    public List<Department> getAll() {
        return repository.findAll();
    }

    public Department getByPositionName(String positionName) {
        return repository.findByPositionName(positionName).orElseThrow(
                () -> new ResourceNotFoundException("Отдела с такой должностью не существует!"));
    }
}
