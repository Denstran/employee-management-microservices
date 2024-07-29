package org.example.administrationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.administrationservice.model.Money;
import org.example.administrationservice.model.companyBranch.Address;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private Long id;
    private Name name;
    private String phoneNumber;
    private Address homeAddress;
    private String email;
    private Money salary;
    private EmployeeStatus employeeStatus;
    private Long companyBranchId;
    private Long positionId;
    private Date employmentDate;
    private String positionName;
    private String departmentName;
    private Integer yearsOfWorking;
}
