package org.example.administrationservice.event.department;

import org.springframework.stereotype.Component;

@Component
public class DepartmentInfoKafkaEventPublisher {

    public void publishDepartmentInfoRegisteredEvent(DepartmentInfoRegistered event) {
        throw new UnsupportedOperationException("Not implemented operation yet");
    }

    public void publishDepartmentInfoBudgetAdjustedEvent(DepartmentInfoBudgetAdjusted event) {
        throw new UnsupportedOperationException("Not implemented operation yet");
    }

    public void publishDepartmentInfoBudgetReducedEvent(DepartmentInfoBudgetReduced event) {
        throw new UnsupportedOperationException("Not implemented operation yet");
    }
    public void publishDepartmentInfoRemovedEvent(DepartmentInfoRemoved event) {
        throw new UnsupportedOperationException("Not implemented operation yet");
    }
}
