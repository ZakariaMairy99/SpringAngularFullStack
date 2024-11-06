package net.zakaria.springangular.repository;

import net.zakaria.springangular.entities.Payment;
import net.zakaria.springangular.entities.PaymentStatus;
import net.zakaria.springangular.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentCode(String Code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
