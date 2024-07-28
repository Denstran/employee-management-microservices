package org.example.paymentlogservice.repository;

import org.example.paymentlogservice.model.CompanyBranchPaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyBranchPaymentLogRepository extends JpaRepository<CompanyBranchPaymentLog, Long>,
        JpaSpecificationExecutor<CompanyBranchPaymentLog> {
}
