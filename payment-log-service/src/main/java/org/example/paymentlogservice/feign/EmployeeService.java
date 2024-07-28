package org.example.paymentlogservice.feign;

import org.example.paymentlogservice.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "employee-service")
public interface EmployeeService {

    @GetMapping("/companyBranches/{companyBranchId}/employees")
    List<EmployeeDTO> getEmployeesFromBranch(@PathVariable Long companyBranchId);

    @GetMapping("/companyBranches/{companyBranchId}/departments/{departmentId}/employees")
    List<EmployeeDTO> getEmployeesFromBranchAndDepartment(@PathVariable Long companyBranchId,
                                                          @PathVariable Long departmentId);

    @GetMapping("/employees/employee/{employeeId}")
    EmployeeDTO getEmployeeById(@PathVariable Long employeeId);
}
