package org.example.paymentlogservice.dto.mapper;

import org.example.dtomapping.AbstractMapper;
import org.example.paymentlogservice.dto.DepartmentInfoPaymentLogDTO;
import org.example.paymentlogservice.model.DepartmentInfoPaymentLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentInfoPaymentLogMapper extends AbstractMapper<DepartmentInfoPaymentLog, DepartmentInfoPaymentLogDTO> {
    /**
     * Constructor for creating a mapper
     *
     * @param entityClass - class of the entity
     * @param dtoClass    - class of the dto
     * @param mapper      - ModelMapper from library, used for mapping objects
     */
    @Autowired
    public DepartmentInfoPaymentLogMapper(Class<DepartmentInfoPaymentLog> entityClass,
                                          Class<DepartmentInfoPaymentLogDTO> dtoClass,
                                          ModelMapper mapper) {
        super(entityClass, dtoClass, mapper);
    }
}
