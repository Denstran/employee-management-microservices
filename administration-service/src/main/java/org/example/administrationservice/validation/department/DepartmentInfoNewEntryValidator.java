package org.example.administrationservice.validation.department;

import org.example.administrationservice.dto.DepartmentInfoDTO;
import org.example.administrationservice.model.Money;
import org.example.administrationservice.model.companyBranch.CompanyBranch;
import org.example.administrationservice.service.CompanyBranchService;
import org.example.administrationservice.service.DepartmentInfoService;
import org.example.administrationservice.validation.ValidatorQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@ValidatorQualifier(validatorKey = "departmentInfoSubValidator")
public class DepartmentInfoNewEntryValidator implements Validator {
    private final CompanyBranchService companyBranchService;
    private final DepartmentInfoService departmentInfoService;

    @Autowired
    public DepartmentInfoNewEntryValidator(CompanyBranchService companyBranchService,
                                           DepartmentInfoService departmentInfoService) {
        this.companyBranchService = companyBranchService;
        this.departmentInfoService = departmentInfoService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return DepartmentInfoDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        DepartmentInfoDTO dto = (DepartmentInfoDTO) target;
        CompanyBranch companyBranch = companyBranchService.getById(dto.getCompanyBranchId());
        if (departmentInfoService.existsById(dto.getCompanyBranchId(), dto.getDepartmentId())) return;

        if (Money.compareTo(companyBranch.getBudget(), dto.getDepartmentBudget()) < 0)
            errors.rejectValue("departmentBudget", "", "Выделенный бюджет превышает бюджет филиала!");
    }
}
