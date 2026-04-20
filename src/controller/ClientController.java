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

    public void addClientByName(String clientName) throws DatabaseException {
        clientRepo.addClientByName(clientName);
    }

    public List<Client> getClientLike(String subName) throws DatabaseException {
        return clientRepo.getClientLike(subName);
    }
}
