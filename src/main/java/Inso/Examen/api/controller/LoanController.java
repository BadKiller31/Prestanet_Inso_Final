package Inso.Examen.api.controller;

import Inso.Examen.api.core.service.LoanService;
import Inso.Examen.domain.dto.LoanDTO;
import Inso.Examen.domain.dto.PaymentScheduleDTO;
import Inso.Examen.domain.dto.ClientNaturalDTO;
import Inso.Examen.domain.dto.ClientJuridicalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/natural")
    public LoanDTO createLoanForNaturalPerson(@RequestBody ClientNaturalDTO clientNaturalDTO) {
        return loanService.createLoanForNaturalPerson(clientNaturalDTO);
    }

    @PostMapping("/juridical")
    public LoanDTO createLoanForJuridicalPerson(@RequestBody ClientJuridicalDTO clientJuridicalDTO) {
        return loanService.createLoanForJuridicalPerson(clientJuridicalDTO);
    }

    @PutMapping("/calculate")
    public void calculateLoanInterestAndInstallments(@RequestBody LoanDTO loanDTO) {
        loanService.calculateLoanInterestAndInstallments(loanDTO);
    }

    @GetMapping("/")
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PutMapping("/{loanId}/status")
    public void changeLoanStatus(@PathVariable Long loanId, @RequestParam String status) {
        loanService.changeLoanStatus(loanId, status);
    }

    @GetMapping("/{loanId}/schedule")
    public List<PaymentScheduleDTO> getPaymentSchedule(@PathVariable Long loanId) {
        return loanService.getPaymentSchedule(loanId);
    }
}
