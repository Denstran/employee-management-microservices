package org.example.paymentlogservice.controller;

import org.example.paymentlogservice.dto.DepartmentInfoPaymentLogDTO;
import org.example.paymentlogservice.dto.mapper.DepartmentInfoPaymentLogMapper;
import org.example.paymentlogservice.service.DepartmentInfoPaymentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments/companyBranch/{companyBranchId}/departments")
public class DepartmentInfoPaymentLogController {
    private final DepartmentInfoPaymentLogService service;
    private final DepartmentInfoPaymentLogMapper mapper;

    @Autowired
    public DepartmentInfoPaymentLogController(DepartmentInfoPaymentLogService service,
                                              DepartmentInfoPaymentLogMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentInfoPaymentLogDTO>> getAllDepartmentInfoPaymentsOfBranch(
            @PathVariable Long companyBranchId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String transferAction) {
        List<DepartmentInfoPaymentLogDTO> departmentInfoPaymentLogDTOS =
                mapper.toDtoList(service.getDepartmentPayments(
                        companyBranchId,
                        null,
                        startDate,
                        endDate,
                        transferAction));

        return ResponseEntity.ok(departmentInfoPaymentLogDTOS);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<List<DepartmentInfoPaymentLogDTO>> getAllDepartmentInfoPaymentsOfBranch(
            @PathVariable Long companyBranchId,
            @PathVariable Long departmentId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String transferAction) {
        List<DepartmentInfoPaymentLogDTO> departmentInfoPaymentLogDTOS =
                mapper.toDtoList(service.getDepartmentPayments(
                        companyBranchId,
                        String.valueOf(departmentId),
                        startDate,
                        endDate,
                        transferAction));

        return ResponseEntity.ok(departmentInfoPaymentLogDTOS);
    }
}
