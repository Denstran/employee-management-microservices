package org.example.taskservice.service.feign;

import org.example.taskservice.dto.EmployeeDTO;
<<<<<<< HEAD
import org.springframework.stereotype.Service;

@Service
// TODO: IMPLEMENT FEIGN CLIENT
public interface EmployeeService {
    EmployeeDTO getByEmail(String email);
=======
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "employee-service")
// TODO: CHANGE URL TO REAL ONE WHEN EMPLOYEE SERVICE IS READY
public interface EmployeeService {
    @GetMapping("/employees/employee/getByEmail")
    EmployeeDTO getByEmail(@RequestParam String email);
>>>>>>> 4588b81 (Refactor task model to use 'taskOwnerEmail' instead of 'taskOwnerIdEmail' and implement employee service with validation for new task DTOs. Add ResourceNotFoundException and EmployeeDTO to enhance task management capabilities. Create TaskMapper to map task entity to dto and vice versa)
}
