package org.example.administrationservice.repository;


import org.example.administrationservice.model.companyBranch.Address;
import org.example.administrationservice.model.companyBranch.CompanyBranch;
import org.example.administrationservice.model.department.DepartmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyBranchRepository extends JpaRepository<CompanyBranch, Long> {

    Optional<CompanyBranch> findCompanyBranchByPhoneNumber(String phoneNumber);
    Optional<CompanyBranch> findCompanyBranchByCompanyBranchAddress(Address address);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByCompanyBranchAddress(Address address);

    @Query("SELECT di FROM DepartmentInfo di WHERE di.pk.companyBranch.id = :companyBranchId")
    List<DepartmentInfo> getDepartments(@Param("companyBranchId") Long companyBranchId);
}
