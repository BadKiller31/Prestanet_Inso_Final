package Inso.Examen.infra.repository;

import Inso.Examen.domain.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Método para verificar si existe un préstamo para un cliente natural
    boolean existsByClientNaturalId(Long clientId);

    // Método para verificar si existe un préstamo para un cliente jurídico
    boolean existsByClientJuridicalId(Long clientId);
}
