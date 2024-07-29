package org.example.paymentlogservice.dto.mapper;

import org.example.dtomapping.AbstractMapper;
import org.example.paymentlogservice.dto.EmployeePaymentLogDTO;
import org.example.paymentlogservice.model.EmployeePaymentLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeePaymentLogMapper extends AbstractMapper<EmployeePaymentLog, EmployeePaymentLogDTO> {

    @Autowired
    public EmployeePaymentLogMapper(Class<EmployeePaymentLog> entityClass,
                                    Class<EmployeePaymentLogDTO> dtoClass,
                                    ModelMapper mapper) {
        super(entityClass, dtoClass, mapper);
    }
}
