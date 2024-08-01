package org.example.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private Long id;
    private Name name;
    private String phoneNumber;
    private String email;
    private EmployeeStatus employeeStatus;

    public String getContacts() {
        return name.toString() + " " + email + " " + phoneNumber;
    }
}
