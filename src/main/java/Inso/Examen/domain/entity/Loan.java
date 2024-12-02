package Inso.Examen.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "loans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Cliente Natural
    @ManyToOne
    @JoinColumn(name = "client_natural_id", nullable = true)
    private ClientNatural clientNatural;

    // Relación con Cliente Jurídico
    @ManyToOne
    @JoinColumn(name = "client_juridical_id", nullable = true)
    private ClientJuridical clientJuridical;

    // Tipo de cliente (Natural o Jurídico)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClientType clientType;

    @Column(nullable = false)
    private BigDecimal amount;  // Monto del préstamo

    @Column(nullable = false)
    private int durationMonths;  // Duración en meses (1 o 6 meses)

    @Column(nullable = false)
    private BigDecimal interest;  // Interés calculado

    @Column(nullable = false)
    private BigDecimal totalAmountToPay;  // Total a pagar con intereses

    @Enumerated(EnumType.STRING)
    private LoanStatus status;  // Estado del préstamo (PENDIENTE, APROBADO, RECHAZADO)

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentSchedule> paymentSchedules;  // Relación con los cronogramas de pago

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Relación con el usuario (quien gestionó el préstamo)

    private LocalDate dateRequested;  // Fecha de solicitud del préstamo

    // Método para asignar estado por defecto a "PENDING" si no se asigna otro estado
    @PrePersist
    public void setDefaultStatus() {
        if (this.status == null) {
            this.status = LoanStatus.PENDING;
        }
    }

    // Enum para indicar el tipo de cliente (Natural o Jurídico)
    public enum ClientType {
        NATURAL, JURIDICAL
    }

    // Enum para el estado del préstamo
    public enum LoanStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}
