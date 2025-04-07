package com.example.payroll.services.IMPL;


import com.example.payroll.entity.Payment;
import com.example.payroll.entity.Payroll;
import com.example.payroll.repository.PaymentRepository;
import com.example.payroll.repository.PayrollRepository;
import com.example.payroll.services.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PayrollRepository payrollRepository;

    @Override
    public Payment createPayment(Payment payment) {
        String employeeId = payment.getEmployeeId();

        // Utilisation de la méthode personnalisée
        Payroll payroll = payrollRepository.findTopByEmployeeIdOrderByPayDateDesc(employeeId)
                .orElseThrow(() -> new RuntimeException("Aucun payroll trouvé pour cet employé"));

        // Associer automatiquement l'ID du payroll
        payment.setPayrollId(payroll.getId());

        // Calculer le montant total du paiement
        Double totalAmount = payroll.getSalary() + payroll.getBonus();
        payment.setAmount(totalAmount);

        // Par sécurité, réaffecter employeeId
        payment.setEmployeeId(payroll.getEmployeeId());

        // Si la date de paiement est nulle, utiliser la date du payroll
        if (payment.getPaymentDate() == null) {
            payment.setPaymentDate(payroll.getPayDate());
        }

        // Générer un numéro de référence
        payment.generateReferenceNumber(payroll.getPayDate());

        return paymentRepository.save(payment);
    }


    @Override
    public Payment getPaymentById(String id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getPaymentsByEmployeeId(String employeeId) {
        return paymentRepository.findByEmployeeId(employeeId);
    }

    @Override
    public Payment updatePayment(String id, Payment updatedPayment) {
        Optional<Payment> existing = paymentRepository.findById(id);
        if (existing.isPresent()) {
            updatedPayment.setId(id);
            return paymentRepository.save(updatedPayment);
        }
        return null;
    }

    @Override
    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }
}