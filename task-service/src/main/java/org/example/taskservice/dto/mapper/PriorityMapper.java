package org.example.taskservice.dto.mapper;

import org.example.dtomapping.AbstractMapper;
import org.example.taskservice.dto.PriorityDTO;
import org.example.taskservice.model.Priority;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriorityMapper extends AbstractMapper<Priority, PriorityDTO> {

    @Autowired
    public PriorityMapper(ModelMapper mapper) {
        super(Priority.class, PriorityDTO.class, mapper);
    }
}
