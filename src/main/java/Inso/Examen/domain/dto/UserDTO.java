package Inso.Examen.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private String role;
}
