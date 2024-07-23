package org.example.administrationservice.validation.companyBranch;

import org.example.administrationservice.dto.CompanyBranchDTO;
import org.example.administrationservice.validation.ValidatorQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class CompanyBranchValidator implements Validator {
    private final List<Validator> subValidators;

    @Autowired
    public CompanyBranchValidator(@ValidatorQualifier(validatorKey = "companyBranchSubValidator")
                                  List<Validator> subValidators) {
        this.subValidators = subValidators;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CompanyBranchDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        subValidators.forEach(validator -> validator.validate(target, errors));
    }
}
