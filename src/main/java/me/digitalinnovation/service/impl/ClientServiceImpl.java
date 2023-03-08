package me.digitalinnovation.service.impl;

import me.digitalinnovation.model.Client;
import me.digitalinnovation.repository.ClientRepository;
import me.digitalinnovation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Iterable<Client> fetchAll() {
        return clientRepository.findAll();
    }
}
