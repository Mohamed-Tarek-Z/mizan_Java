package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import model.sqlcon;
import exceptions.DatabaseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

    private final sqlcon dbConnection;

    public UserDAO(sqlcon dbConnection) {
        this.dbConnection = dbConnection;
    }

    public User authenticate(String username, String password) throws DatabaseException {
        try {
            ResultSet resultSet = dbConnection.dataRead("status_", "users",
                    "username=N'" + username + "' and password_=N'" + password + "'");
            if (resultSet.next()) {
                boolean isAdmin = "0".equals(resultSet.getString(1));
                return new User(username, password, isAdmin);
            }

            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء التسجيل الدخول", ex);
        }
    }
}
