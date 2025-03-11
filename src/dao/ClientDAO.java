package dao;

import exceptions.DatabaseException;
import model.sqlcon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;

public class ClientDAO {

    private final sqlcon dbConnection;

    public ClientDAO(sqlcon dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addClientByName(String clientName) throws DatabaseException {
        try {
            if (!dbConnection.dataRead("*", "clients", "cli_name=N'" + clientName + "'").next()) {
                dbConnection.inData("clients", "cli_name", "N'" + clientName + "'");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناءعرض الشكارة", ex);
        }
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
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناءعرض الشكارة", ex);
        }
    }
    
    // add func to remove Dublicates
}
