package Inso.Examen.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientJuridical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String businessName;
    private String ruc;  // RUC de la persona jurídica

    // Constructor sin ID, para uso de DTO
    public ClientJuridical(String businessName, String ruc) {
        this.businessName = businessName;
        this.ruc = ruc;
    }
}
