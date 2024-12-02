package Inso.Examen.infra.repository;

import Inso.Examen.domain.entity.ClientJuridical;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientJuridicalRepository extends JpaRepository<ClientJuridical, Long> {

    // Método para encontrar un cliente por su RUC
    Optional<ClientJuridical> findByRuc(String ruc);

    // Verifica si existe un cliente con el RUC
    boolean existsByRuc(String ruc);
}
