package org.example.paymentlogservice.dto.mapper;

import org.example.dtomapping.AbstractMapper;
import org.example.paymentlogservice.dto.CompanyBranchPaymentLogDTO;
import org.example.paymentlogservice.model.CompanyBranchPaymentLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyBranchPaymentLogMapper extends AbstractMapper<CompanyBranchPaymentLog, CompanyBranchPaymentLogDTO> {
    /**
     * Constructor for creating a mapper
     *
     * @param entityClass - class of the entity
     * @param dtoClass    - class of the dto
     * @param mapper      - ModelMapper from library, used for mapping objects
     */
    @Autowired
    public CompanyBranchPaymentLogMapper(Class<CompanyBranchPaymentLog> entityClass,
                                         Class<CompanyBranchPaymentLogDTO> dtoClass,
                                         ModelMapper mapper) {
        super(entityClass, dtoClass, mapper);
    }
}
