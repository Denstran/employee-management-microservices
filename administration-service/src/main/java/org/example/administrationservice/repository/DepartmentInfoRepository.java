package org.example.administrationservice.repository;


import org.example.administrationservice.model.CompanyBranchDepartmentPK;
import org.example.administrationservice.model.department.DepartmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentInfoRepository extends JpaRepository<DepartmentInfo, CompanyBranchDepartmentPK> {
    List<DepartmentInfo> findByPk_CompanyBranch_Id(Long companyBranchId);

}
