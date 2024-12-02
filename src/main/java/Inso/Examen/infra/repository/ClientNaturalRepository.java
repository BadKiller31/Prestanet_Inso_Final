package Inso.Examen.infra.repository;

import Inso.Examen.domain.entity.ClientNatural;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientNaturalRepository extends JpaRepository<ClientNatural, Long> {

    // Método para encontrar un cliente por su DNI
    Optional<ClientNatural> findByDni(String dni);

    // Verifica si existe un cliente con el DNI
    boolean existsByDni(String dni);
}
