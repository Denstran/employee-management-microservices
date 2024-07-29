package org.example.paymentlogservice.controller;

import org.example.paymentlogservice.dto.CompanyBranchPaymentLogDTO;
import org.example.paymentlogservice.dto.mapper.CompanyBranchPaymentLogMapper;
import org.example.paymentlogservice.service.CompanyBranchPaymentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments/companyBranchPayments")
public class CompanyBranchPaymentLogController {
    private final CompanyBranchPaymentLogService service;
    private final CompanyBranchPaymentLogMapper mapper;

    @Autowired
    public CompanyBranchPaymentLogController(CompanyBranchPaymentLogService service,
                                             CompanyBranchPaymentLogMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{companyBranchId}")
    public ResponseEntity<List<CompanyBranchPaymentLogDTO>> getPaymentsOfCompanyBranch(
            @PathVariable Long companyBranchId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String transferAction) {

        List<CompanyBranchPaymentLogDTO> companyBranchPaymentLogDTOS =
                mapper.toDtoList(service.getCompanyBranchPayments(companyBranchId, startDate, endDate, transferAction));

        return ResponseEntity.ok(companyBranchPaymentLogDTOS);
    }
}
