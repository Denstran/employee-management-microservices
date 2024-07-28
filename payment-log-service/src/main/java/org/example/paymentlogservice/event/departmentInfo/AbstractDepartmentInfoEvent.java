package org.example.paymentlogservice.event.departmentInfo;

import lombok.Data;

@Data
public abstract class AbstractDepartmentInfoEvent {
    private final Long companyBranchId;
    private final Long departmentId;
}
