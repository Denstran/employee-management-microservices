package org.example.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ValidationErrorResponse {
    private List<Violation> fieldViolations = new ArrayList<>();
    private List<String> globalViolations = new ArrayList<>();

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Violation {
        private String fieldName;
        private String message;
    }

    public static ValidationErrorResponse createValidationErrorResponse(BindingResult bindingResult) {
        ValidationErrorResponse response = new ValidationErrorResponse();

        for (FieldError fieldError : bindingResult.getFieldErrors())
            response.getFieldViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));

        for (ObjectError globalError : bindingResult.getGlobalErrors())
            response.getGlobalViolations().add(globalError.getDefaultMessage());

        return response;
    }
}

