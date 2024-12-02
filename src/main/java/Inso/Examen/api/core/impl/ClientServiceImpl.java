package Inso.Examen.api.core.impl;

import Inso.Examen.domain.dto.ClientJuridicalDTO;
import Inso.Examen.domain.dto.ClientNaturalDTO;
import Inso.Examen.domain.entity.ClientJuridical;
import Inso.Examen.domain.entity.ClientNatural;
import Inso.Examen.infra.exception.ClientDniNotFoundException;
import Inso.Examen.infra.exception.ClientRucNotFoundException;
import Inso.Examen.infra.repository.ClientJuridicalRepository;
import Inso.Examen.infra.repository.ClientNaturalRepository;
import Inso.Examen.api.core.service.ClientService;
import Inso.Examen.util.DocumentValidator; // Importamos la clase de validación
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientNaturalRepository clientNaturalRepository;
    private final ClientJuridicalRepository clientJuridicalRepository;

    @Autowired
    public ClientServiceImpl(ClientNaturalRepository clientNaturalRepository,
                             ClientJuridicalRepository clientJuridicalRepository) {
        this.clientNaturalRepository = clientNaturalRepository;
        this.clientJuridicalRepository = clientJuridicalRepository;
    }

    @Override
    public ClientNatural validateClientDni(String dni) {
        return clientNaturalRepository.findByDni(dni)
                .orElseThrow(() -> new ClientDniNotFoundException(dni)); // Lanzamos la excepción personalizada
    }

    @Override
    public ClientJuridical validateClientRuc(String ruc) {
        return clientJuridicalRepository.findByRuc(ruc)
                .orElseThrow(() -> new ClientRucNotFoundException(ruc)); // Lanzamos la excepción personalizada
    }

    @Override
    public ClientNatural addClientNatural(ClientNaturalDTO clientDTO) {
        // Validar el formato del DNI
        if (!DocumentValidator.validateDni(clientDTO.getDni())) {
            throw new IllegalArgumentException("El DNI debe tener exactamente 8 dígitos numéricos.");
        }

        if (clientNaturalRepository.existsByDni(clientDTO.getDni())) {
            throw new IllegalArgumentException("Client with DNI " + clientDTO.getDni() + " already exists");
        }
        ClientNatural client = new ClientNatural();
        client.setName(clientDTO.getName());
        client.setDni(clientDTO.getDni());

        return clientNaturalRepository.save(client);
    }

    @Override
    public ClientJuridical addClientJuridical(ClientJuridicalDTO clientDTO) {
        if (clientJuridicalRepository.existsByRuc(clientDTO.getRuc())) {
            throw new IllegalArgumentException("Client with RUC " + clientDTO.getRuc() + " already exists");
        }
        ClientJuridical client = new ClientJuridical();
        client.setBusinessName(clientDTO.getBusinessName());
        client.setRuc(clientDTO.getRuc());

        return clientJuridicalRepository.save(client);
    }
    @Override
    public List<ClientNatural> getAllClientsNatural() {
        return clientNaturalRepository.findAll();
    }

    @Override
    public List<ClientJuridical> getAllClientsJuridical() {
        return clientJuridicalRepository.findAll();
    }
}