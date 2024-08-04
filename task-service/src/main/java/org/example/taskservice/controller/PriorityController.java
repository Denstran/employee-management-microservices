package org.example.taskservice.controller;

import jakarta.validation.Valid;
import org.example.taskservice.dto.PriorityDTO;
import org.example.taskservice.dto.ValidationErrorResponse;
import org.example.taskservice.dto.mapper.PriorityMapper;
import org.example.taskservice.model.Priority;
import org.example.taskservice.service.PriorityService;
import org.example.taskservice.validation.PriorityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/priorities")
public class PriorityController {
    private final PriorityService priorityService;
    private final PriorityMapper priorityMapper;
    private final PriorityValidator priorityValidator;

    @Autowired
    public PriorityController(PriorityService priorityService,
                              PriorityMapper priorityMapper,
                              PriorityValidator priorityValidator) {
        this.priorityService = priorityService;
        this.priorityMapper = priorityMapper;
        this.priorityValidator = priorityValidator;
    }

    @GetMapping("/companyBranch/{companyBranchId}/department/{departmentId}")
    public ResponseEntity<List<PriorityDTO>> getPriorities(@PathVariable Long companyBranchId,
                                                           @PathVariable Long departmentId) {
        List<PriorityDTO> priorityDTOS = priorityMapper.toDtoList(
                priorityService.getByCompanyBranchIdAndDepartmentId(companyBranchId, departmentId));

        return ResponseEntity.ok(priorityDTOS);
    }

    @GetMapping("/priority/{priorityId}")
    public ResponseEntity<PriorityDTO> getById(@PathVariable Long priorityId) {
        PriorityDTO priorityDTO = priorityMapper.toDto(priorityService.getById(priorityId));

        return ResponseEntity.ok(priorityDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPriority(@RequestBody @Valid PriorityDTO priorityDTO,
                                            BindingResult bindingResult) {
        priorityValidator.validate(priorityDTO, bindingResult);
        if (bindingResult.hasErrors())
            setupErrorResponse(bindingResult);

        Priority priority = priorityMapper.toEntity(priorityDTO);
        priorityService.createPriority(priority);

        return ResponseEntity.ok("Created");
    }

    @PatchMapping("/priority/{priorityId}/update")
    public ResponseEntity<?> updatePriority(@RequestBody @Valid PriorityDTO priorityDTO,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return setupErrorResponse(bindingResult);


        Priority priority = priorityMapper.toEntity(priorityDTO);
        priorityService.updatePriority(priority);

        return ResponseEntity.ok("Created");
    }

    private ResponseEntity<ValidationErrorResponse> setupErrorResponse(BindingResult bindingResult) {
        ValidationErrorResponse errorResponse =
                ValidationErrorResponse.createValidationErrorResponse(bindingResult);

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
