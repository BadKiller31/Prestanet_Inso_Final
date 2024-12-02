package Inso.Examen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientNaturalDTO {

    private String name;  // Nombre del cliente natural
    private String dni;   // DNI del cliente natural

}
