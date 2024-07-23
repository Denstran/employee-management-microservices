package org.example.administrationservice.validation.department;

import org.example.administrationservice.dto.DepartmentInfoDTO;
import org.example.administrationservice.validation.ValidatorQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class DepartmentInfoValidator implements Validator {
    private final List<Validator> subValidators;

    @Autowired
    public DepartmentInfoValidator(@ValidatorQualifier(validatorKey = "departmentInfoSubValidator") List<Validator> subValidators) {
        this.subValidators = subValidators;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return DepartmentInfoDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        subValidators.forEach(validator -> validator.validate(target, errors));
    }
}
