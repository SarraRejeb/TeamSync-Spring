package com.example.payroll.repository;

import com.example.payroll.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findByEmployeeId(String employeeId);
    List<Payment> findByPayrollId(String payrollId);





}
