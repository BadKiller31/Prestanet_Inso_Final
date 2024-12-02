package Inso.Examen.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentScheduleDTO {
    private LocalDate paymentDate;
    private BigDecimal amountToPay;
    private BigDecimal interestAmount;
    private boolean isPaid;

    // Constructor
    public PaymentScheduleDTO(LocalDate paymentDate, BigDecimal amountToPay, BigDecimal interestAmount, boolean isPaid) {
        this.paymentDate = paymentDate;
        this.amountToPay = amountToPay;
        this.interestAmount = interestAmount;
        this.isPaid = isPaid;
    }

    // Getters y Setters
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(BigDecimal amountToPay) {
        this.amountToPay = amountToPay;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
