package org.example.administrationservice.event.companyBranch;

import lombok.Data;

@Data
public abstract class AbstractCompanyBranchEvent {
    private final Long companyBranchId;
}
