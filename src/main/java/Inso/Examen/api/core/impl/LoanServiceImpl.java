package Inso.Examen.api.core.impl;

import Inso.Examen.api.core.service.LoanService;
import Inso.Examen.domain.dto.LoanDTO;
import Inso.Examen.domain.dto.PaymentScheduleDTO;
import Inso.Examen.domain.dto.ClientNaturalDTO;
import Inso.Examen.domain.dto.ClientJuridicalDTO;
import Inso.Examen.domain.entity.ClientJuridical;
import Inso.Examen.domain.entity.ClientNatural;
import Inso.Examen.domain.entity.Loan;
import Inso.Examen.domain.entity.PaymentSchedule;
import Inso.Examen.infra.repository.LoanRepository;
import Inso.Examen.infra.repository.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private PaymentScheduleRepository paymentScheduleRepository;

    @Override
    public LoanDTO createLoanForNaturalPerson(ClientNaturalDTO clientNaturalDTO) {
        // Lógica para crear un préstamo para una persona natural
        Loan loan = new Loan();
        loan.setClientNatural(new ClientNatural()); // Aquí asignas el cliente natural, no el ID
        loan.getClientNatural().setName(clientNaturalDTO.getName()); // Asignar valores desde el DTO
        loan.setAmount(BigDecimal.valueOf(5000));  // Monto máximo
        loan.setDateRequested(LocalDate.now());
        loan.setClientType(Loan.ClientType.NATURAL);  // Tipo de cliente
        loanRepository.save(loan);

        return new LoanDTO(clientNaturalDTO.getName(), BigDecimal.valueOf(5000), 1, BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now());
    }

    @Override
    public LoanDTO createLoanForJuridicalPerson(ClientJuridicalDTO clientJuridicalDTO) {
        // Lógica para crear un préstamo para una persona jurídica
        Loan loan = new Loan();
        loan.setClientJuridical(new ClientJuridical()); // Aquí asignas el cliente jurídico, no el ID
        loan.getClientJuridical().setBusinessName(clientJuridicalDTO.getBusinessName()); // Asignar valores desde el DTO
        loan.setAmount(BigDecimal.valueOf(5000));  // Monto máximo
        loan.setDateRequested(LocalDate.now());
        loan.setClientType(Loan.ClientType.JURIDICAL);  // Tipo de cliente
        loanRepository.save(loan);

        return new LoanDTO(clientJuridicalDTO.getBusinessName(), BigDecimal.valueOf(5000), 6, BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now());
    }

    @Override
    public void calculateLoanInterestAndInstallments(LoanDTO loanDTO) {
        // Lógica para calcular los intereses y cuotas
        BigDecimal interestRate = loanDTO.getDurationMonths() == 1 ? new BigDecimal("0.10") : new BigDecimal("0.20");
        loanDTO.setInterest(loanDTO.getAmount().multiply(interestRate));
        loanDTO.setTotalAmountToPay(loanDTO.getAmount().add(loanDTO.getInterest()));

        // Crear el cronograma de pagos
        generatePaymentSchedule(loanDTO);
    }

    @Override
    public List<PaymentScheduleDTO> generatePaymentSchedule(LoanDTO loanDTO) {
        List<PaymentScheduleDTO> paymentSchedules = new ArrayList<>();
        BigDecimal monthlyPayment = loanDTO.getTotalAmountToPay().divide(BigDecimal.valueOf(loanDTO.getDurationMonths()), BigDecimal.ROUND_HALF_UP);
        for (int i = 1; i <= loanDTO.getDurationMonths(); i++) {
            PaymentScheduleDTO paymentSchedule = new PaymentScheduleDTO(
                    LocalDate.now().plusMonths(i),
                    monthlyPayment,
                    monthlyPayment.multiply(loanDTO.getInterest().divide(loanDTO.getAmount())),
                    false
            );
            paymentSchedules.add(paymentSchedule);
        }
        return paymentSchedules;
    }

    @Override
    public boolean loanExists(Long clientId) {
        return loanRepository.existsByClientNaturalId(clientId) || loanRepository.existsByClientJuridicalId(clientId);
    }

    @Override
    public List<LoanDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        List<LoanDTO> loanDTOs = new ArrayList<>();
        for (Loan loan : loans) {
            loanDTOs.add(new LoanDTO(
                    loan.getClientNatural() != null ? loan.getClientNatural().getName() : loan.getClientJuridical().getBusinessName(),
                    loan.getAmount(),
                    6,
                    loan.getInterest(),
                    loan.getTotalAmountToPay(),
                    loan.getDateRequested()
            ));
        }
        return loanDTOs;
    }

    @Override
    public void changeLoanStatus(Long loanId, String status) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus(Loan.LoanStatus.valueOf(status)); // Convertir el string a LoanStatus
        loanRepository.save(loan);
    }

    @Override
    public List<PaymentScheduleDTO> getPaymentSchedule(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        List<PaymentSchedule> paymentSchedules = paymentScheduleRepository.findByLoanId(loanId);
        List<PaymentScheduleDTO> paymentScheduleDTOs = new ArrayList<>();
        for (PaymentSchedule paymentSchedule : paymentSchedules) {
            paymentScheduleDTOs.add(new PaymentScheduleDTO(
                    paymentSchedule.getPaymentDate(),
                    paymentSchedule.getAmountToPay(),
                    paymentSchedule.getInterestAmount(),
                    paymentSchedule.isPaid()
            ));
        }
        return paymentScheduleDTOs;
    }
}
