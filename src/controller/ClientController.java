package controller;

import repository.ClientRepository;
import exceptions.DatabaseException;
import java.util.List;
import model.Client;

public class ClientController {

    private final ClientRepository clientRepo;

    public ClientController(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Client addClientByName(String clientName) throws DatabaseException {
        return clientRepo.addClientByName(clientName);
    }

    public List<Client> getClientLike(String subName) throws DatabaseException {
        return clientRepo.getClientLike(subName);
    }

    public List<Client> getClients() throws DatabaseException {
        return clientRepo.getClients();
    }

    public boolean clientExists(String name) throws DatabaseException {
        return clientRepo.clientExists(name);
    }
}
