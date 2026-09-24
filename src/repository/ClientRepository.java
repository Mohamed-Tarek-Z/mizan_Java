package repository;

import exceptions.DatabaseException;
import model.sqlcon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;

public class ClientRepository {

    private final sqlcon dbConnection;

    public ClientRepository(sqlcon dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Client addClientByName(String clientName) throws DatabaseException {
        try {
            if (!clientExists(clientName)) {
                dbConnection.inData("clients", "cli_name", "N'" + clientName + "'");
            }
            ResultSet rs = dbConnection.dataRead("cli_id,cli_name", "clients", "cli_name = N'" + clientName + "'");
            if (rs.next()) {
                return new Client(rs.getInt("cli_id"), rs.getString("cli_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ في إضافة عميل", ex);
        }
        return null;
    }

    public List<Client> getClientLike(String subName) throws DatabaseException {
        try {
            List<Client> clients = new ArrayList<>();
            ResultSet rs = dbConnection.dataRead("cli_id,cli_name", "clients", "cli_name like N'%" + subName.strip() + "%' ");
            while (rs.next()) {
                clients.add(new Client(rs.getInt("cli_id"), rs.getString("cli_name")));
            }
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناءعرض العملاء", ex);
        }
    }

    public List<Client> getClients() throws DatabaseException {
        try {
            List<Client> clients = new ArrayList<>();
            ResultSet rs = dbConnection.dataRead("cli_id,cli_name", "clients");
            while (rs.next()) {
                clients.add(new Client(rs.getInt("cli_id"), rs.getString("cli_name")));
            }
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناءعرض العملاء", ex);
        }
    }

    public boolean clientExists(String name) throws DatabaseException {
        try {
            return dbConnection.dataRead("*", "clients", "cli_name=N'" + name + "'").next();
        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناءعرض عميل", ex);
        }
    }
}
