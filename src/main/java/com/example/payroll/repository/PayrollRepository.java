package com.example.payroll.repository;

import com.example.payroll.entity.Payroll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRepository extends MongoRepository<Payroll, String> {
}
