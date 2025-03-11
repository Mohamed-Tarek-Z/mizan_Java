package controller;

import dao.ClientDAO;
import exceptions.DatabaseException;
import java.util.List;
import model.Client;

public class ClientController {

    private final ClientDAO clientDAO;

    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void addClientByName(String clientName) throws DatabaseException {
        clientDAO.addClientByName(clientName);
    }

    public List<Client> getClientLike(String subName) throws DatabaseException {
        return clientDAO.getClientLike(subName);
    }
}
