package org.example.administrationservice.service;

import org.example.administrationservice.model.Position;
import org.example.administrationservice.model.department.Department;
import org.example.administrationservice.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Transactional
    public void create(Position position) {
        positionRepository.saveAndFlush(position);
    }

    @Transactional
    public void update(Position position) {
        Position positionFromDB = getById(position.getId());
        if (!positionFromDB.isLeading() == position.isLeading())
            position.changeLeading();

        positionRepository.save(position);
    }

    public List<Position> getAllPositions(Department department, String isLeading) {
        Specification<Position> spec = Specification.where(null);

        if (isLeading != null && !isLeading.isEmpty() && !isLeading.equals("all")) {
            boolean leading = isLeading.equalsIgnoreCase("true");
            spec = spec.and(PositionSpec.isLeading(leading));
        }

        if (department != null)
            spec = spec.and(PositionSpec.isEqualToDepartment(department));


        return positionRepository.findAll(spec);
    }

    public Optional<Position> getByName(String positionName) {
        return positionRepository.findByPositionNameIgnoreCase(positionName);
    }

    public Optional<Position> getByLeadingAndDepartmentId(boolean isLeading, Long departmentId) {
        return positionRepository.findByLeadingAndDepartment_Id(isLeading, departmentId);
    }

    public boolean existsByLeadingAndDepartmentId(boolean isLeading, Long departmentId) {
        return positionRepository.existsByLeadingAndDepartment_Id(isLeading, departmentId);
    }

    public boolean existsByPositionName(String positionName) {
        return positionRepository.existsByPositionNameIgnoreCase(positionName);
    }

    public Position getById(Long positionId) {
        return positionRepository.findById(positionId).orElseThrow(()
                -> new IllegalArgumentException("Выбранной должности не существует!"));
    }
}
