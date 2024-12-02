package Inso.Examen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientJuridicalDTO {

    private String businessName; // Razón social de la persona jurídica
    private String ruc;          // RUC de la persona jurídica

}
