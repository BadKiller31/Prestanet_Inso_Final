package Inso.Examen.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment_schedules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    private LocalDate paymentDate;

    private BigDecimal amountToPay;

    private BigDecimal interestAmount;

    private boolean isPaid;
}
