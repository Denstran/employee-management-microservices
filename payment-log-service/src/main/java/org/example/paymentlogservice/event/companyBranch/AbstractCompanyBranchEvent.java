package org.example.paymentlogservice.event.companyBranch;

import lombok.Data;

@Data
public abstract class AbstractCompanyBranchEvent {
    private final Long companyBranchId;
}
