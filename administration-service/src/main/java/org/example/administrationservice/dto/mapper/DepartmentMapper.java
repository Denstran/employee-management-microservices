package org.example.administrationservice.dto.mapper;


import org.example.administrationservice.dto.DepartmentDTO;
import org.example.administrationservice.model.department.Department;
import org.example.dtomapping.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of AbstractMapper for Department and DepartmentDTO
 */
@Component
public class DepartmentMapper extends AbstractMapper<Department, DepartmentDTO> {

    @Autowired
    public DepartmentMapper(ModelMapper modelMapper) {
        super(Department.class, DepartmentDTO.class, modelMapper);
    }

}
