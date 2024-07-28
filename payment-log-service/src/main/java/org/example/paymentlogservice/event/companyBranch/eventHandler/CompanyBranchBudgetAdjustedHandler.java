package org.example.paymentlogservice.event.companyBranch.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentlogservice.event.EventHandler;
import org.example.paymentlogservice.event.HandlerQualifier;
import org.example.paymentlogservice.event.companyBranch.CompanyBranchBudgetAdjusted;
import org.example.paymentlogservice.model.CompanyBranchPaymentLog;
import org.example.paymentlogservice.service.CompanyBranchPaymentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@HandlerQualifier(handlerKey = "companyBranchPaymentEvent")
@Component
@Slf4j
public class CompanyBranchBudgetAdjustedHandler implements EventHandler {
    private static final String COMPANY_BRANCH_BUDGET_ADJUSTED = "CompanyBranchBudgetAdjusted";
    private final ObjectMapper objectMapper;
    private final CompanyBranchPaymentLogService paymentLogService;

    @Autowired
    public CompanyBranchBudgetAdjustedHandler(ObjectMapper objectMapper,
                                              CompanyBranchPaymentLogService paymentLogService) {
        this.objectMapper = objectMapper;
        this.paymentLogService = paymentLogService;
    }


    @Override
    public void handleEvent(String eventType, String event) {
        if (!COMPANY_BRANCH_BUDGET_ADJUSTED.equals(eventType)) return;
        log.info("Handling event: {}, expected event: {}", eventType, COMPANY_BRANCH_BUDGET_ADJUSTED);

        try {
            CompanyBranchBudgetAdjusted companyBranchEvent =
                    objectMapper.readValue(event, CompanyBranchBudgetAdjusted.class);

            CompanyBranchPaymentLog paymentLog = CompanyBranchPaymentLog.createPaymentLog(companyBranchEvent);

            log.info("Handled success! Saving to DB");
            paymentLogService.saveCompanyBranchPaymentLog(paymentLog);
        } catch (JsonProcessingException e) {
            log.error("Error during handling event {}, error message {}", eventType, e.getMessage());
            throw new RuntimeException("Can't read event: " + event + " for CompanyBranchCreated class");
        }
    }
}
