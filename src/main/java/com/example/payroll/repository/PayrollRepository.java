package com.example.payroll.repository;

import com.example.payroll.entity.Payroll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends MongoRepository<Payroll, String> {


}
