package org.example.taskservice.service.feign;

import org.example.taskservice.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

@Service
// TODO: IMPLEMENT FEIGN CLIENT
public interface EmployeeService {
    EmployeeDTO getByEmail(String email);
}
