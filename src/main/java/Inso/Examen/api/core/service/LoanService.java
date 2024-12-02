package Inso.Examen.api.core.service;

import Inso.Examen.domain.dto.LoanDTO;
import Inso.Examen.domain.dto.PaymentScheduleDTO;
import Inso.Examen.domain.dto.ClientNaturalDTO;
import Inso.Examen.domain.dto.ClientJuridicalDTO;

import java.util.List;

public interface LoanService {

    // Crear una solicitud de préstamo para un cliente natural
    LoanDTO createLoanForNaturalPerson(ClientNaturalDTO clientNaturalDTO);

    // Crear una solicitud de préstamo para un cliente jurídico
    LoanDTO createLoanForJuridicalPerson(ClientJuridicalDTO clientJuridicalDTO);

    // Calcular el interés y las cuotas del préstamo
    void calculateLoanInterestAndInstallments(LoanDTO loanDTO);

    // Generar el cronograma de pagos
    List<PaymentScheduleDTO> generatePaymentSchedule(LoanDTO loanDTO);

    // Verificar si un préstamo ya existe para un cliente
    boolean loanExists(Long clientId);

    // Obtener todos los préstamos
    List<LoanDTO> getAllLoans();

    // Cambiar el estado de un préstamo (Aprobado/Rechazado)
    void changeLoanStatus(Long loanId, String status);

    // Ver detalles de un cronograma de pago
    List<PaymentScheduleDTO> getPaymentSchedule(Long loanId);
}
