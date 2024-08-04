package org.example.taskservice.service.feign;

import org.example.taskservice.dto.DepartmentInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "administration-service")
public interface AdministrationService {
    @GetMapping("/admin/companyBranches/companyBranch/{companyBranchId}/departments/{departmentId}")
    DepartmentInfoDTO getDepartmentInfoByCompanyBranchAndDepartment(@PathVariable Long companyBranchId,
                                                                    @PathVariable Long departmentId);
}
