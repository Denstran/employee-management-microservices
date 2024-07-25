package org.example.administrationservice.repository;

import org.example.administrationservice.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long>, JpaSpecificationExecutor<Position> {
    List<Position> getPositionsByDepartmentId(Long departmentId);

    boolean existsByLeadingAndDepartment_Id(boolean isLeading, Long departmentId);
    boolean existsByPositionNameIgnoreCase(String positionName);

    Optional<Position> findByLeadingAndDepartment_Id(boolean isLeading, Long departmentId);

    Optional<Position> findByPositionNameIgnoreCase(String positionName);
}
