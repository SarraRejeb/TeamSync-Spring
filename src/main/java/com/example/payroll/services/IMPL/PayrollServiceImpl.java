package com.example.payroll.services.IMPL;

import com.example.payroll.entity.Payroll;
import com.example.payroll.entity.Employee;
import com.example.payroll.repository.PayrollRepository;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.services.interfaces.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public PayrollServiceImpl(PayrollRepository payrollRepository, EmployeeRepository employeeRepository) {
        this.payrollRepository = payrollRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    @Override
    public Payroll getPayrollById(String id) {
        return payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found with id: " + id));
    }

    @Override
    public Payroll savePayroll(Payroll payroll) {
        // Sauvegarder le payroll
        Payroll savedPayroll = payrollRepository.save(payroll);

        // Ajouter le payroll à l'employé
        Employee employee = employeeRepository.findById(payroll.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));



        // Sauvegarder l'employé avec le nouveau payroll
        employeeRepository.save(employee);

        return savedPayroll;
    }


    @Override
    public Payroll updatePayroll(String id, Payroll payrollDetails) {
        Payroll existingPayroll = payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found with id: " + id));

        // Mettre à jour le payroll avec les nouvelles données
        existingPayroll.setSalary(payrollDetails.getSalary());
        existingPayroll.setPayDate(payrollDetails.getPayDate());
        existingPayroll.setBonus(payrollDetails.getBonus());
        existingPayroll.setEmployeeId(payrollDetails.getEmployeeId());

        return payrollRepository.save(existingPayroll);
    }

    @Override
    public void deletePayroll(String id) {
        payrollRepository.deleteById(id);
    }

}