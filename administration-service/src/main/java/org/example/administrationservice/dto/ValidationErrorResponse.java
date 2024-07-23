package org.example.administrationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ValidationErrorResponse {
    private List<Violation> violations = new ArrayList<>();

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Violation {
        private String fieldName;
        private String message;
    }

    public static ValidationErrorResponse createValidationErrorResponse(BindingResult bindingResult) {
        ValidationErrorResponse response = new ValidationErrorResponse();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            response.getViolations().add(
                    new ValidationErrorResponse.Violation(fieldError.getField(), fieldError.getDefaultMessage())
            );
        }

        return response;
    }
}

