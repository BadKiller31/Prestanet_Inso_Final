package Inso.Examen.api.core.impl;

import Inso.Examen.api.core.service.UserService ;
import Inso.Examen.domain.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void createUser(UserDTO userDTO) {
        // Lógica para crear un usuario
        // Validación y creación del usuario en la base de datos
    }

    @Override
    public String login(UserDTO userDTO) {
        // Lógica para iniciar sesión de un usuario
        // Validación de las credenciales
        return "JWT Token";  // Retorna el token JWT
    }
}
