package org.example.administrationservice.repository;


import org.example.administrationservice.model.department.CompanyBranchDepartmentPK;
import org.example.administrationservice.model.department.DepartmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentInfoRepository extends JpaRepository<DepartmentInfo, CompanyBranchDepartmentPK> {
    List<DepartmentInfo> findByPk_CompanyBranch_Id(Long companyBranchId);

    @Query("select case when count(di)> 0 then true else false end from DepartmentInfo di " +
            "where di.pk.companyBranch.id = :companyBranchId and di.pk.department.id = :departmentId")
    boolean existsById(@Param("companyBranchId") Long companyBranchId,
                       @Param("departmentId") Long departmentId);

    Optional<DepartmentInfo> findDepartmentInfoByPk_CompanyBranch_IdAndPk_Department_Id(Long companyBranchId,
                                                                                        Long departmentId);
}
