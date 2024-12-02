package Inso.Examen.api.core.service;

import Inso.Examen.domain.dto.UserDTO;
import Inso.Examen.domain.dto.ClientNaturalDTO;
import Inso.Examen.domain.dto.ClientJuridicalDTO;

public interface UserService {

    // Crear un nuevo usuario
    void createUser(UserDTO userDTO);

    // Iniciar sesión de un usuario
    String login(UserDTO userDTO);
}
