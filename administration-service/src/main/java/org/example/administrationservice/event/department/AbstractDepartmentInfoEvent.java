package org.example.administrationservice.event.department;

import lombok.Data;

@Data
public abstract class AbstractDepartmentInfoEvent {
    private final Long companyBranchId;
    private final Long departmentId;
}
