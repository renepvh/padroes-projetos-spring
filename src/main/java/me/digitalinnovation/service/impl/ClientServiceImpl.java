package me.digitalinnovation.service.impl;

import me.digitalinnovation.model.Address;
import me.digitalinnovation.model.Client;
import me.digitalinnovation.repository.AddressRepository;
import me.digitalinnovation.repository.ClientRepository;
import me.digitalinnovation.service.ByZipCode;
import me.digitalinnovation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ByZipCode byZipCode;
    @Override
    public Iterable<Client> fetchAll() {
        return clientRepository.findAll();
    }

    @Override
    public void insert(Client client) {
        saveCustomerWithZipCode(client);
    }

    @Override
    public Client fetchById(Long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public void update(Long id, Client client) {
        Optional<Client> clientDB = clientRepository.findById(id);
        if (clientDB.isPresent()) {
            saveCustomerWithZipCode(client);
        }
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public void saveCustomerWithZipCode(Client client) {
        String zipCode = client.getAddress().getCep();
        Address address = addressRepository.findById(zipCode).orElseGet(() -> {
            Address newAddress = byZipCode.consultZipCode(zipCode);
            addressRepository.save(newAddress);
            return newAddress;
        });
        client.setAddress(address);
        clientRepository.save(client);
    }
}
