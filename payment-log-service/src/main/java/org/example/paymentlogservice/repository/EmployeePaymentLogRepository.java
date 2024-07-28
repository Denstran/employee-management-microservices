package org.example.paymentlogservice.repository;

import org.example.paymentlogservice.model.EmployeePaymentLog;
import org.example.paymentlogservice.model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface EmployeePaymentLogRepository extends JpaRepository<EmployeePaymentLog, Long>,
        JpaSpecificationExecutor<EmployeePaymentLog> {
    List<EmployeePaymentLog> findByDateOfPaymentBetweenAndEmployeeIdAndPaymentTypeIn(Date beginning, Date end,
                                                                                   Long employeeId,
                                                                                   List<PaymentType> paymentTypes);
}
