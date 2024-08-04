package org.example.taskservice.validation;

import org.example.taskservice.dto.DepartmentInfoDTO;
import org.example.taskservice.dto.PriorityDTO;
import org.example.taskservice.service.PriorityService;
import org.example.taskservice.service.feign.AdministrationService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@ValidatorQualifier(validatorKey = "prioritySubValidator")
public class PriorityNewEntryValidator implements Validator {
    private final PriorityService priorityService;
    private final AdministrationService administrationService;

    public PriorityNewEntryValidator(PriorityService priorityService,
                                     AdministrationService administrationService) {
        this.priorityService = priorityService;
        this.administrationService = administrationService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PriorityDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PriorityDTO dto = (PriorityDTO) target;
        if (dto.getId() != null) return;
        validateDepartmentExistence(dto, errors);
        validateTitle(dto, errors);
    }

    private void validateDepartmentExistence(PriorityDTO dto, Errors errors) {
        try {
            DepartmentInfoDTO departmentInfoDTO =
                    administrationService
                            .getDepartmentInfoByCompanyBranchAndDepartment(dto.getCompanyBranchId(), dto.getDepartmentId());
        } catch (Exception e) {
            errors.reject("", "Отдела с указанными параметрами не существует!");
        }
    }

    private void validateTitle(PriorityDTO dto, Errors errors) {
        if (priorityService.existsByTitleCompanyBranchDepartment(dto.getTitle(), dto.getCompanyBranchId(), dto.getDepartmentId()))
            errors.rejectValue("title", "", "Приоритет с таким названием уже существует!");
    }
}
