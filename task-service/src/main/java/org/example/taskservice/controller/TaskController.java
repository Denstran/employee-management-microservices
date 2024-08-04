package org.example.taskservice.controller;

import jakarta.validation.Valid;
import org.example.taskservice.dto.TaskDTO;
import org.example.taskservice.dto.TaskExtensionDeadlineDTO;
import org.example.taskservice.dto.mapper.TaskMapper;
import org.example.taskservice.dto.ValidationErrorResponse;
import org.example.taskservice.model.Task;
import org.example.taskservice.service.TaskService;
import org.example.taskservice.validation.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final TaskValidator taskValidator;

    @Autowired
    public TaskController(TaskService taskService,
                          TaskMapper taskMapper,
                          TaskValidator taskValidator) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
        this.taskValidator = taskValidator;
    }

    @GetMapping("/employeeTasks")
    public ResponseEntity<List<TaskDTO>> getEmployeeTasks(@RequestParam String employeeEmail) {
        List<TaskDTO> employeeTasks = taskMapper.toDtoList(taskService.getByOwnerEmail(employeeEmail));

        return ResponseEntity.ok(employeeTasks);
    }

    @GetMapping("/headOfDepartment/givenTasks")
    public ResponseEntity<List<TaskDTO>> getGivenTasks(@RequestParam String giverEmail) {
        List<TaskDTO> employeeTasks = taskMapper.toDtoList(taskService.getByGiverEmail(giverEmail));

        return ResponseEntity.ok(employeeTasks);
    }

    @PostMapping("/headOfDepartment/createTask")
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskDTO taskDTO,
                                             BindingResult bindingResult) {
        taskValidator.validate(taskDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            ValidationErrorResponse errorResponse =
                    ValidationErrorResponse.createValidationErrorResponse(bindingResult);

            return ResponseEntity.badRequest().body(errorResponse);
        }

        Task task = taskMapper.toEntity(taskDTO);
        taskService.createTask(task);

        return ResponseEntity.ok(task);
    }

    @PatchMapping("/finishTask/{taskId}")
    public ResponseEntity<String> finishTask(@PathVariable Long taskId) {
        taskService.finishTask(taskService.getById(taskId));

        return ResponseEntity.ok("Задача отправлена на проверку");
    }

    @PatchMapping("/approveTask/{taskId}")
    public ResponseEntity<String> approveTask(@PathVariable Long taskId) {
        taskService.approveTask(taskService.getById(taskId));

        return ResponseEntity.ok("Задача одобрена");
    }

    @PatchMapping("/disapproveTask/{taskId}")
    public ResponseEntity<String> disapproveTask(@PathVariable Long taskId) {
        taskService.disapproveTask(taskService.getById(taskId));

        return ResponseEntity.ok("Задача не одобрена");
    }

    @PatchMapping("/cancelTask/{taskId}")
    public ResponseEntity<String> cancelTask(@PathVariable Long taskId) {
        taskService.cancelTask(taskService.getById(taskId));

        return ResponseEntity.ok("Задача завершена");
    }

    @PatchMapping("/extendTask/{taskId}")
    public ResponseEntity<?> extendTaskDeadline(@RequestBody @Valid TaskExtensionDeadlineDTO extensionDeadlineDTO,
                                                     BindingResult bindingResult,
                                                     @PathVariable Long taskId) {
        if (bindingResult.hasErrors()) {
            ValidationErrorResponse errorResponse =
                    ValidationErrorResponse.createValidationErrorResponse(bindingResult);

            return ResponseEntity.badRequest().body(errorResponse);
        }

        Task task = taskService.getById(taskId);
        task.setTaskDeadLine(extensionDeadlineDTO.getExtendedDeadline());

        taskService.extendTaskDeadline(task);

        return ResponseEntity.ok("Задача продлена");
    }
}
