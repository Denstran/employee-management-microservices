package org.example.administrationservice.feign;

import org.example.administrationservice.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "employee-service")
public interface EmployeeService {
    @GetMapping("/companyBranches/{companyBranchId}/departments/{departmentId}/employees")
    List<EmployeeDTO> getEmployeesFromCompanyBranchAndDepartment(@PathVariable Long companyBranchId,
                                                                 @PathVariable Long departmentId);
}
