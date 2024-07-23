package org.example.administrationservice.controller;

import jakarta.validation.Valid;
import org.example.administrationservice.dto.CompanyBranchDTO;
import org.example.administrationservice.dto.ValidationErrorResponse;
import org.example.administrationservice.dto.mapper.CompanyBranchMapper;
import org.example.administrationservice.service.CompanyBranchService;
import org.example.administrationservice.validation.companyBranch.CompanyBranchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/companyBranches")
public class CompanyBranchController {
    private final CompanyBranchService companyBranchService;
    private final CompanyBranchValidator validator;
    private final CompanyBranchMapper mapper;

    @Autowired
    public CompanyBranchController(CompanyBranchService companyBranchService,
                                   CompanyBranchValidator validator,
                                   CompanyBranchMapper mapper) {
        this.companyBranchService = companyBranchService;
        this.validator = validator;
        this.mapper = mapper;
    }

    @GetMapping("/companyBranch/{companyBranchId}")
    public ResponseEntity<CompanyBranchDTO> getCompanyBranch(@PathVariable Long companyBranchId) {
        CompanyBranchDTO dto = mapper.toDto(companyBranchService.getById(companyBranchId));

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CompanyBranchDTO>> getAllCompanyBranches() {
        List<CompanyBranchDTO> companyBranchDTOS = mapper.toDtoList(companyBranchService.getAll());
        return ResponseEntity.ok(companyBranchDTOS);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCompanyBranch(@RequestBody @Valid CompanyBranchDTO companyBranchDTO,
                                                 BindingResult bindingResult) {
        validator.validate(companyBranchDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            ValidationErrorResponse errorResponse =
                    ValidationErrorResponse.createValidationErrorResponse(bindingResult);

            return ResponseEntity.badRequest().body(errorResponse);
        }

        companyBranchService.create(mapper.toEntity(companyBranchDTO));

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PatchMapping("/companyBranch/{companyBranchId}/update")
    public ResponseEntity<?> updateCompanyBranch(@RequestBody @Valid CompanyBranchDTO companyBranchDTO,
                                                 BindingResult bindingResult,
                                                 @PathVariable Long companyBranchId) {
        validator.validate(companyBranchDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            ValidationErrorResponse errorResponse =
                    ValidationErrorResponse.createValidationErrorResponse(bindingResult);

            return ResponseEntity.badRequest().body(errorResponse);
        }

        companyBranchService.update(mapper.toEntity(companyBranchDTO));

        return ResponseEntity.ok("Created");
    }
}
