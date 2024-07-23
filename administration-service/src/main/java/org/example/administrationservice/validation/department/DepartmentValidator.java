package org.example.administrationservice.validation.department;

import org.example.administrationservice.dto.DepartmentDTO;
import org.example.administrationservice.validation.ValidatorQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class DepartmentValidator implements Validator {
    private final List<Validator> subValidators;

    @Autowired
    public DepartmentValidator(@ValidatorQualifier(validatorKey = "departmentSubValidator")
                               List<Validator> subValidators) {
        this.subValidators = subValidators;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return DepartmentDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        subValidators.forEach(validator -> validator.validate(target, errors));
    }
}
