package org.example.taskservice.dto.mapper;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.example.dtomapping.AbstractMapperWithSpecificFields;
import org.example.taskservice.dto.EmployeeDTO;
import org.example.taskservice.dto.TaskDTO;
import org.example.taskservice.model.Task;
import org.example.taskservice.service.PriorityService;
import org.example.taskservice.service.feign.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskMapper extends AbstractMapperWithSpecificFields<Task, TaskDTO> {
    private final PriorityService priorityService;
    private final EmployeeService employeeService;

    @Autowired
    protected TaskMapper(ModelMapper mapper, PriorityService priorityService,
                         EmployeeService employeeService) {
        super(Task.class, TaskDTO.class, mapper);
        this.priorityService = priorityService;
        this.employeeService = employeeService;
    }

    @Override
    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Task.class, TaskDTO.class)
                .addMappings(m -> {
                    m.skip(TaskDTO::setPriorityId);
                    m.skip(TaskDTO::setGiverContacts);
                    m.skip(TaskDTO::setOwnerContacts);
                }).setPostConverter(toDtoConverter());

        mapper.createTypeMap(TaskDTO.class, Task.class)
                .addMappings(m -> m.skip(Task::setPriority)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFieldsForDto(Task source, TaskDTO destination) {
        log.info("Mapping Task entity: {} to TaskDTO", source);
        destination.setPriorityId(source.getPriority().getId());
        EmployeeDTO owner = employeeService.getByEmail(source.getTaskOwnerEmail());
        EmployeeDTO giver = employeeService.getByEmail(source.getTaskGiverEmail());

        destination.setGiverContacts(giver.getContacts());
        destination.setOwnerContacts(owner.getContacts());
        log.info("Mapping finished: final TaskDTO: {}", destination);
    }

    @Override
    protected void mapSpecificFieldsForEntity(TaskDTO source, Task destination) {
        log.info("Mapping TaskDTO: {} to Task entity", source);
        destination.setPriority(priorityService.getById(source.getPriorityId()));
        log.info("Mapping finished: final Task entity: {}", destination);
    }
}
