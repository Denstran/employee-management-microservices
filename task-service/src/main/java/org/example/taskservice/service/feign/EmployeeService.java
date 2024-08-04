package org.example.taskservice.service.feign;

import org.example.taskservice.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "employee-service")
// TODO: CHANGE URL TO REAL ONE WHEN EMPLOYEE SERVICE IS READY
public interface EmployeeService {
    @GetMapping("/employees/employee/getByEmail")
    EmployeeDTO getByEmail(@RequestParam String email);
}
