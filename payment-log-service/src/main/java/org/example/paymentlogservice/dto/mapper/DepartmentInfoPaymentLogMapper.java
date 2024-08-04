package org.example.paymentlogservice.dto.mapper;

import org.example.dtomapping.AbstractMapper;
import org.example.paymentlogservice.dto.DepartmentInfoPaymentLogDTO;
import org.example.paymentlogservice.model.DepartmentInfoPaymentLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentInfoPaymentLogMapper extends AbstractMapper<DepartmentInfoPaymentLog, DepartmentInfoPaymentLogDTO> {

    @Autowired
    public DepartmentInfoPaymentLogMapper(ModelMapper mapper) {
        super(DepartmentInfoPaymentLog.class, DepartmentInfoPaymentLogDTO.class, mapper);
    }
}
