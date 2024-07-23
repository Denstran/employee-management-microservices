package org.example.administrationservice.controller;

import jakarta.validation.Valid;
import org.example.administrationservice.dto.DepartmentDTO;
import org.example.administrationservice.dto.ValidationErrorResponse;
import org.example.administrationservice.dto.mapper.DepartmentMapper;
import org.example.administrationservice.model.department.Department;
import org.example.administrationservice.service.DepartmentService;
import org.example.administrationservice.validation.department.DepartmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/departments")
public class DepartmentController {
    private final DepartmentService service;
    private final DepartmentValidator validator;
    private final DepartmentMapper mapper;

    @Autowired
    public DepartmentController(DepartmentService service,
                                DepartmentValidator validator,
                                DepartmentMapper mapper) {
        this.service = service;
        this.validator = validator;
        this.mapper = mapper;
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long departmentId) {
        DepartmentDTO departmentDTO = mapper.toDto(service.getById(departmentId));

        return ResponseEntity.ok(departmentDTO);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departmentDTOS = mapper.toDtoList(service.getAll());

        return ResponseEntity.ok(departmentDTOS);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO,
                                              BindingResult bindingResult) {
        validator.validate(departmentDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            ValidationErrorResponse errorResponse =
                    ValidationErrorResponse.createValidationErrorResponse(bindingResult);

            return ResponseEntity.badRequest().body(errorResponse);
        }

        Department department = mapper.toEntity(departmentDTO);
        service.create(department);

        return ResponseEntity.ok("Created");
    }

    @PatchMapping("/department/{departmentId}/update")
    public ResponseEntity<?> updateDepartment(@RequestBody @Valid DepartmentDTO departmentDTO,
                                              BindingResult bindingResult) {
        validator.validate(departmentDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            ValidationErrorResponse errorResponse =
                    ValidationErrorResponse.createValidationErrorResponse(bindingResult);

            return ResponseEntity.badRequest().body(errorResponse);
        }

        Department department = mapper.toEntity(departmentDTO);
        service.update(department);

        return ResponseEntity.ok("Created");
    }
}
