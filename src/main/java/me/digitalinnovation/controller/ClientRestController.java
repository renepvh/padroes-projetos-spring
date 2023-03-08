package me.digitalinnovation.controller;

import me.digitalinnovation.model.Client;
import me.digitalinnovation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Iterable<Client>> fetchAll() {
        return ResponseEntity.ok(clientService.fetchAll());
    }

    @PostMapping
    public ResponseEntity<Client> insert(@RequestBody Client client) {
        clientService.insert(client);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> fetchById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.fetchById(id));
    }

}
