package me.digitalinnovation.controller;

import jakarta.validation.Valid;
import me.digitalinnovation.model.Client;
import me.digitalinnovation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Client> insert(@RequestBody @Valid Client client) {
        clientService.insert(client);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> fetchById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.fetchById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
        clientService.update(id, client);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}
