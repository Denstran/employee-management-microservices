package org.example.taskservice.validation;

import org.example.taskservice.dto.PriorityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class PriorityValidator implements Validator {
    private final List<Validator> subValidators;

    @Autowired
    public PriorityValidator(@ValidatorQualifier(validatorKey = "prioritySubValidator")
                             List<Validator> subValidators) {
        this.subValidators = subValidators;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PriorityDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PriorityDTO dto = (PriorityDTO) target;
        subValidators.forEach(validator -> validator.validate(dto, errors));
    }
}
