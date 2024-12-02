package Inso.Examen.api.core.service;

import Inso.Examen.domain.dto.ClientJuridicalDTO;
import Inso.Examen.domain.dto.ClientNaturalDTO;
import Inso.Examen.domain.entity.ClientJuridical;
import Inso.Examen.domain.entity.ClientNatural;

import java.util.List;

public interface ClientService {

    ClientNatural validateClientDni(String dni);

    ClientJuridical validateClientRuc(String ruc);

    ClientNatural addClientNatural(ClientNaturalDTO clientDTO);

    ClientJuridical addClientJuridical(ClientJuridicalDTO clientDTO);

    List<ClientNatural> getAllClientsNatural();

    List<ClientJuridical> getAllClientsJuridical();
}
