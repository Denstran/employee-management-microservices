package org.example.administrationservice.service;

import org.example.administrationservice.model.Position;
import org.example.administrationservice.model.department.Department;
import org.springframework.data.jpa.domain.Specification;

public class PositionSpec {

    public static Specification<Position> isLeading(boolean isLeading) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("leading"), isLeading);
    }

    public static Specification<Position> isEqualToDepartment(Department department) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("department"), department);
    }
}
