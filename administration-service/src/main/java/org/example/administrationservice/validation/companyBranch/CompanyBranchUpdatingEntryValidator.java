package org.example.administrationservice.validation.companyBranch;

import org.example.administrationservice.dto.CompanyBranchDTO;
import org.example.administrationservice.model.companyBranch.CompanyBranch;
import org.example.administrationservice.service.CompanyBranchService;
import org.example.administrationservice.validation.ValidatorQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@ValidatorQualifier(validatorKey = "companyBranchSubValidator")
public class CompanyBranchUpdatingEntryValidator implements Validator {

    private final CompanyBranchService companyBranchService;

    @Autowired
    public CompanyBranchUpdatingEntryValidator(CompanyBranchService companyBranchService) {
        this.companyBranchService = companyBranchService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CompanyBranchDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CompanyBranchDTO dto = (CompanyBranchDTO) target;
        if (dto.getId() == null) return;

        Optional<CompanyBranch> companyBranchByAddress = companyBranchService
                .getByAddress(dto.getCompanyBranchAddress());

        Optional<CompanyBranch> companyBranchByPhone = companyBranchService
                .getByPhoneNumber(dto.getPhoneNumber());

        if (!isSameEntity(companyBranchByAddress, dto.getId()))
            errors.rejectValue("companyBranchAddress", "", "Филиал с таким адресом уже существует!");

        if (!isSameEntity(companyBranchByPhone, dto.getId()))
            errors.rejectValue("phoneNumber", "", "Филиал с таким номером телефона уже существует!");
    }

    private boolean isSameEntity(Optional<CompanyBranch> entityFromDb, Long dtoId) {
        return entityFromDb.isPresent() && entityFromDb.get().getId().equals(dtoId);
    }
}
