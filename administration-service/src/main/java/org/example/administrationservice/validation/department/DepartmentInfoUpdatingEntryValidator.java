package org.example.administrationservice.validation.department;

import org.example.administrationservice.dto.DepartmentInfoDTO;
import org.example.administrationservice.model.Money;
import org.example.administrationservice.model.companyBranch.CompanyBranch;
import org.example.administrationservice.model.department.DepartmentInfo;
import org.example.administrationservice.service.CompanyBranchService;
import org.example.administrationservice.service.DepartmentInfoService;
import org.example.administrationservice.validation.ValidatorQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@ValidatorQualifier(validatorKey = "departmentInfoSubValidator")
public class DepartmentInfoUpdatingEntryValidator implements Validator {
    private final DepartmentInfoService departmentInfoService;
    private final CompanyBranchService companyBranchService;

    @Autowired
    public DepartmentInfoUpdatingEntryValidator(DepartmentInfoService departmentInfoService,
                                                CompanyBranchService companyBranchService) {
        this.departmentInfoService = departmentInfoService;
        this.companyBranchService = companyBranchService;
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

        if (!departmentInfoService.existsById(dto.getCompanyBranchId(), dto.getCompanyBranchId())) return;

        DepartmentInfo departmentInfo = departmentInfoService.getById(dto.getCompanyBranchId(), dto.getDepartmentId());

        if (dto.getDepartmentBudget().equals(departmentInfo.getDepartmentBudget())) return;

        Money budgetIncrease = Money.subtract(dto.getDepartmentBudget(), departmentInfo.getDepartmentBudget());

        if (Money.compareTo(companyBranch.getBudget(), budgetIncrease) < 0)
            errors.rejectValue("departmentBudget", "", "Повышение бюджета превышает бюджет филиала!");

    }
}
