package org.example.paymentlogservice.repository;

import org.example.paymentlogservice.model.DepartmentInfoPaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentInfoPaymentLogRepository extends JpaRepository<DepartmentInfoPaymentLog, Long>,
        JpaSpecificationExecutor<DepartmentInfoPaymentLog> {
}
