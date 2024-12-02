package Inso.Examen.infra.repository;

import Inso.Examen.domain.entity.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, Long> {
    List<PaymentSchedule> findByLoanId(Long loanId); // Para obtener los pagos asociados a un préstamo
}
