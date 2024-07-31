package org.example.paymentlogservice.controller;

import jakarta.validation.Valid;
import org.example.paymentlogservice.dto.EmployeePaymentLogDTO;
import org.example.paymentlogservice.dto.ValidationErrorResponse;
import org.example.paymentlogservice.dto.mapper.EmployeePaymentLogMapper;
import org.example.paymentlogservice.model.EmployeePaymentLog;
import org.example.paymentlogservice.service.EmployeePaymentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments/employeePayments")
public class EmployeePaymentLogController {
    private final EmployeePaymentLogService service;
    private final EmployeePaymentLogMapper mapper;

    @Autowired
    public EmployeePaymentLogController(EmployeePaymentLogService service,
                                        EmployeePaymentLogMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/companyBranch/{companyBranchId}")
    public ResponseEntity<List<EmployeePaymentLogDTO>> getEmployeesPaymentsFromBranch(
            @PathVariable Long companyBranchId,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "departmentId", required = false) Long departmentId) {

        List<EmployeePaymentLogDTO> employeePaymentLogDTOS = mapper.toDtoList(
                service.getAllEmployeesPaymentLogsFromCompanyBranch
                        (companyBranchId, departmentId, startDate, endDate, action));

        return ResponseEntity.ok(employeePaymentLogDTOS);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<EmployeePaymentLogDTO>> getEmployeePayments(
            @PathVariable Long employeeId,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "transferAction", required = false) String transferAction) {
        List<EmployeePaymentLogDTO> paymentLogDTOS = mapper.toDtoList(
                service.getEmployeePaymentLog(employeeId, startDate, endDate, transferAction));

        return ResponseEntity.ok(paymentLogDTOS);
    }

    @PostMapping("/{employeeId}/create")
    public ResponseEntity<?> createEmployeePaymentLog(@RequestBody @Valid EmployeePaymentLogDTO dto,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ValidationErrorResponse errorResponse =
                    ValidationErrorResponse.createValidationErrorResponse(bindingResult);

            return ResponseEntity.badRequest().body(errorResponse);
        }

        EmployeePaymentLog paymentLog = mapper.toEntity(dto);
        service.saveEmployeePaymentLog(paymentLog);

        return ResponseEntity.ok("Created");
    }
}
