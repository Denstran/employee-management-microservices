package org.example.administrationservice.validation.position;

import org.example.administrationservice.dto.PositionDTO;
import org.example.administrationservice.validation.ValidatorQualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class PositionValidator implements Validator {
    private final List<Validator> subValidators;

    public PositionValidator(@ValidatorQualifier(validatorKey = "positionSubValidator")
                             List<Validator> subValidators) {
        this.subValidators = subValidators;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PositionDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        subValidators.forEach(validator -> validator.validate(target, errors));
    }
}
