package Inso.Examen.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanDTO {

    private String clientName;  // Puede ser el nombre del cliente natural o razón social del cliente jurídico
    private BigDecimal amount;
    private int durationMonths;
    private BigDecimal interest;
    private BigDecimal totalAmountToPay;
    private LocalDate dateRequested;

    // Constructor para LoanDTO
    public LoanDTO(String clientName, BigDecimal amount, int durationMonths, BigDecimal interest, BigDecimal totalAmountToPay, LocalDate dateRequested) {
        this.clientName = clientName;
        this.amount = amount;
        this.durationMonths = durationMonths;
        this.interest = interest;
        this.totalAmountToPay = totalAmountToPay;
        this.dateRequested = dateRequested;
    }

    // Getters y Setters
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getTotalAmountToPay() {
        return totalAmountToPay;
    }

    public void setTotalAmountToPay(BigDecimal totalAmountToPay) {
        this.totalAmountToPay = totalAmountToPay;
    }

    public LocalDate getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(LocalDate dateRequested) {
        this.dateRequested = dateRequested;
    }
}
