package org.example.paymentlogservice.event.companyBranch.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentlogservice.event.EventHandler;
import org.example.paymentlogservice.event.HandlerQualifier;
import org.example.paymentlogservice.event.companyBranch.CompanyBranchCreated;
import org.example.paymentlogservice.model.CompanyBranchPaymentLog;
import org.example.paymentlogservice.service.CompanyBranchPaymentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@HandlerQualifier(handlerKey = "companyBranchPaymentEvent")
@Component
@Slf4j
public class CompanyBranchCreatedHandler implements EventHandler {
    private static final String COMPANY_BRANCH_CREATED = "CompanyBranchCreated";
    private final ObjectMapper objectMapper;
    private final CompanyBranchPaymentLogService paymentLogService;

    @Autowired
    public CompanyBranchCreatedHandler(ObjectMapper objectMapper,
                                       CompanyBranchPaymentLogService paymentLogService) {
        this.objectMapper = objectMapper;
        this.paymentLogService = paymentLogService;
    }

    @Override
    public void handleEvent(String eventType, String event) {
        if (!COMPANY_BRANCH_CREATED.equals(eventType)) return;
        log.info("Handling event: {}, expected event: {}", eventType, COMPANY_BRANCH_CREATED);

        try {
            CompanyBranchCreated companyBranchCreated = objectMapper.readValue(event, CompanyBranchCreated.class);
            CompanyBranchPaymentLog paymentLog = CompanyBranchPaymentLog.createPaymentLog(companyBranchCreated);

            paymentLogService.saveCompanyBranchPaymentLog(paymentLog);
        } catch (JsonProcessingException e) {
            log.error("Error during handling event {}, error message {}", eventType, e.getMessage());
            throw new RuntimeException("Can't read event: " + event + " for CompanyBranchCreated class");
        }
    }
}
