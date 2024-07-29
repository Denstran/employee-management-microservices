package org.example.paymentlogservice.event.departmentInfo.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentlogservice.event.EventHandler;
import org.example.paymentlogservice.event.HandlerQualifier;
import org.example.paymentlogservice.event.departmentInfo.DepartmentInfoBudgetAdjusted;
import org.example.paymentlogservice.model.DepartmentInfoPaymentLog;
import org.example.paymentlogservice.service.DepartmentInfoPaymentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@HandlerQualifier(handlerKey = "departmentInfoPaymentEvent")
@Component
@Slf4j
public class DepartmentInfoBudgetAdjustedHandler implements EventHandler {
    private static final String DEPARTMENT_INFO_BUDGET_ADJUSTED = "DepartmentInfoBudgetAdjusted";
    private final ObjectMapper objectMapper;
    private final DepartmentInfoPaymentLogService paymentLogService;

    @Autowired
    public DepartmentInfoBudgetAdjustedHandler(ObjectMapper objectMapper,
                                               DepartmentInfoPaymentLogService paymentLogService) {
        this.objectMapper = objectMapper;
        this.paymentLogService = paymentLogService;
    }

    @Override
    public void handleEvent(String eventType, String event) {
        if (!DEPARTMENT_INFO_BUDGET_ADJUSTED.equals(eventType)) return;

        log.info("Handling event: {}, expected event: {}", eventType, DEPARTMENT_INFO_BUDGET_ADJUSTED);

        try {
            DepartmentInfoBudgetAdjusted departmentInfoEvent =
                    objectMapper.readValue(event, DepartmentInfoBudgetAdjusted.class);

            DepartmentInfoPaymentLog paymentLog = DepartmentInfoPaymentLog.createPaymentLog(departmentInfoEvent);

            log.info("Handled success! Saving to DB");
            paymentLogService.saveDepartmentInfoPaymentLog(paymentLog);
        } catch (JsonProcessingException e) {
            log.error("Error during handling event {}, error message {}", eventType, e.getMessage());
            throw new RuntimeException("Can't read event: " + event + " for DepartmentInfoBudgetAdjusted class");
        }
    }
}
