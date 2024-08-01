package org.example.taskservice.validation;

import org.example.taskservice.dto.EmployeeDTO;
import org.example.taskservice.dto.EmployeeStatus;
import org.example.taskservice.dto.TaskDTO;
import org.example.taskservice.service.feign.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TaskValidator implements Validator {
    private final EmployeeService employeeService;

    @Autowired
    public TaskValidator(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TaskDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TaskDTO dto = (TaskDTO) target;
        EmployeeDTO taskOwner = employeeService.getByEmail(dto.getTaskOwnerEmail());

        if (isFired(taskOwner))
            errors.reject("", "Нельзя выдать задачу уволенному сотруднику!");

        if (isOnVacation(taskOwner))
            errors.reject("", "Нельзя выдать задачу сотруднику в отпуске!");
    }

    private boolean isOnVacation(EmployeeDTO taskOwner) {
        return EmployeeStatus.VACATION.equals(taskOwner.getEmployeeStatus());
    }

    private boolean isFired(EmployeeDTO taskOwner) {
        return EmployeeStatus.FIRED.equals(taskOwner.getEmployeeStatus());
    }
}
