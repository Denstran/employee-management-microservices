package org.example.administrationservice.controller;

import jakarta.validation.Valid;
import org.example.administrationservice.dto.PositionDTO;
import org.example.administrationservice.dto.ValidationErrorResponse;
import org.example.administrationservice.dto.mapper.PositionMapper;
import org.example.administrationservice.model.Position;
import org.example.administrationservice.model.department.Department;
import org.example.administrationservice.service.DepartmentService;
import org.example.administrationservice.service.PositionService;
import org.example.administrationservice.validation.position.PositionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/positions")
public class PositionController {
    private final PositionService service;
    private final DepartmentService departmentService;
    private final PositionMapper mapper;
    private final PositionValidator validator;

    @Autowired
    public PositionController(PositionService service,
                              DepartmentService departmentService,
                              PositionMapper mapper,
                              PositionValidator validator) {
        this.service = service;
        this.departmentService = departmentService;
        this.mapper = mapper;
        this.validator = validator;
    }

    @GetMapping("/position/{positionId}")
    public ResponseEntity<PositionDTO> getPosition(@PathVariable Long positionId) {
        PositionDTO positionDTO = mapper.toDto(service.getById(positionId));

        return ResponseEntity.ok(positionDTO);
    }

    @GetMapping
    public ResponseEntity<List<PositionDTO>> getAllPositionS(
            @RequestParam(value = "department", required = false) String department,
            @RequestParam(value = "isLeading", required = false, defaultValue = "all") String isLeading) {
        Department departmentDB = departmentService.getByName(department).orElse(null);
        List<PositionDTO> positionDTOS = mapper.toDtoList(service.getAllPositions(departmentDB, isLeading));

        return ResponseEntity.ok(positionDTOS);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPosition(@RequestBody @Valid PositionDTO positionDTO,
                                            BindingResult bindingResult) {
        validator.validate(positionDTO, bindingResult);
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(
                    ValidationErrorResponse.createValidationErrorResponse(bindingResult));

        Position position = mapper.toEntity(positionDTO);

        service.create(position);

        return ResponseEntity.ok("Created");
    }

    @PutMapping("/position/{positionId}/update")
    public ResponseEntity<?> updatePosition(@RequestBody @Valid PositionDTO positionDTO,
                                            BindingResult bindingResult) {
        validator.validate(positionDTO, bindingResult);
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(
                    ValidationErrorResponse.createValidationErrorResponse(bindingResult));

        Position position = mapper.toEntity(positionDTO);

        service.update(position);

        return ResponseEntity.ok("Created");
    }
}
