package Inso.Examen.api.controller;

import Inso.Examen.api.core.service.ClientService;
import Inso.Examen.domain.dto.ClientJuridicalDTO;
import Inso.Examen.domain.dto.ClientNaturalDTO;
import Inso.Examen.domain.entity.ClientJuridical;
import Inso.Examen.domain.entity.ClientNatural;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/natural")
    public ResponseEntity<ClientNatural> addClientNatural(@RequestBody ClientNaturalDTO clientDTO) {
        ClientNatural client = clientService.addClientNatural(clientDTO);
        return ResponseEntity.ok(client);
    }

    @PostMapping("/juridical")
    public ResponseEntity<ClientJuridical> addClientJuridical(@RequestBody ClientJuridicalDTO clientDTO) {
        ClientJuridical client = clientService.addClientJuridical(clientDTO);
        return ResponseEntity.ok(client);
    }
}
