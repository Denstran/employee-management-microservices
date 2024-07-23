package org.example.administrationservice.validation.department;


import org.example.administrationservice.dto.DepartmentDTO;
import org.example.administrationservice.model.department.DepartmentType;
import org.example.administrationservice.service.DepartmentService;
import org.example.administrationservice.validation.ValidatorQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@ValidatorQualifier(validatorKey = "departmentSubValidator")
public class DepartmentNewEntryValidation implements Validator {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentNewEntryValidation(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return DepartmentDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DepartmentDTO dto = (DepartmentDTO) target;
        if (dto.getId() != null) return;

        if (departmentService.existsByName(dto.getDepartmentName()))
            errors.rejectValue("departmentName", "", "Отдел с таким название уже существует!");

        if (!dto.getDepartmentType().equals(DepartmentType.OTHER))
            if (departmentService.existsByType(dto.getDepartmentType()))
                errors.rejectValue("departmentName", "", "Отдел с таким типом уже существует!");
    }
}
