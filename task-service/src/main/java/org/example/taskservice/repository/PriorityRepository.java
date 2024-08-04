package org.example.taskservice.repository;

import org.example.taskservice.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
    boolean existsByTitleAndCompanyBranchIdAndDepartmentId(String title,
                                                           Long companyBranchId,
                                                           Long departmentId);

    Optional<Priority> findByTitleAndCompanyBranchIdAndDepartmentId(String title,
                                                                    Long companyBranchId,
                                                                    Long departmentId);
}
