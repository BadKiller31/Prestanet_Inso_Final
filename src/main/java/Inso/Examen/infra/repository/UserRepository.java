package Inso.Examen.infra.repository;

import Inso.Examen.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Método para encontrar un usuario por su nombre de usuario
    User findByUsername(String username);

    // Método para verificar si un usuario con un nombre de usuario específico ya existe
    boolean existsByUsername(String username);
}
