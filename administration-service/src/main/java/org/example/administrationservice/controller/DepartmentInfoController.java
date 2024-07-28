package org.example.administrationservice.controller;

import jakarta.validation.Valid;
import org.example.administrationservice.dto.DepartmentInfoDTO;
import org.example.administrationservice.dto.ValidationErrorResponse;
import org.example.administrationservice.dto.mapper.DepartmentInfoMapper;
import org.example.administrationservice.model.department.DepartmentInfo;
import org.example.administrationservice.service.DepartmentInfoService;
import org.example.administrationservice.validation.department.DepartmentInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/companyBranches/companyBranch/{companyBranchId}/departments")
public class DepartmentInfoController {
    private final DepartmentInfoService service;
    private final DepartmentInfoValidator validator;
    private final DepartmentInfoMapper mapper;

    @Autowired
    public DepartmentInfoController(DepartmentInfoService service,
                                    DepartmentInfoValidator validator,
                                    DepartmentInfoMapper mapper) {
        this.service = service;
        this.validator = validator;
        this.mapper = mapper;
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentInfoDTO> getDepartmentInfo(@PathVariable Long companyBranchId,
                                                               @PathVariable Long departmentId) {
        DepartmentInfoDTO dto = mapper.toDto(service.getById(companyBranchId, departmentId));

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentInfoDTO>> getAllDepartmentInfoOfCompanyBranch(@PathVariable Long companyBranchId) {
        List<DepartmentInfoDTO> dtos = mapper.toDtoList(service.getAllByCompanyBranchId(companyBranchId));

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createDepartmentInfo(@RequestBody @Valid DepartmentInfoDTO dto,
                                                  BindingResult bindingResult) {
        validator.validate(dto, bindingResult);
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest()
                    .body(ValidationErrorResponse.createValidationErrorResponse(bindingResult));

        DepartmentInfo departmentInfo = mapper.toEntity(dto);
        service.createDepartmentInfo(departmentInfo);

        return ResponseEntity.ok("Created");
    }

    @PutMapping("/{departmentId}/update")
    public ResponseEntity<?> updateDepartmentInfo(@RequestBody @Valid DepartmentInfoDTO dto,
                                                  BindingResult bindingResult) {
        validator.validate(dto, bindingResult);
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest()
                    .body(ValidationErrorResponse.createValidationErrorResponse(bindingResult));

        DepartmentInfo departmentInfo = mapper.toEntity(dto);
        service.updateDepartmentInfo(departmentInfo);

        return ResponseEntity.ok("Updated");
    }
}
