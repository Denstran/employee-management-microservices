package org.example.paymentlogservice.dto.mapper;

import org.example.dtomapping.AbstractMapper;
import org.example.paymentlogservice.dto.CompanyBranchPaymentLogDTO;
import org.example.paymentlogservice.model.CompanyBranchPaymentLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyBranchPaymentLogMapper extends AbstractMapper<CompanyBranchPaymentLog, CompanyBranchPaymentLogDTO> {
    @Autowired
    public CompanyBranchPaymentLogMapper(ModelMapper mapper) {
        super(CompanyBranchPaymentLog.class, CompanyBranchPaymentLogDTO.class, mapper);
    }
}
