package me.digitalinnovation.service;

import me.digitalinnovation.model.Client;

public interface ClientService {
    Iterable<Client> fetchAll();

    void insert(Client client);

    Client fetchById(Long id);
}
