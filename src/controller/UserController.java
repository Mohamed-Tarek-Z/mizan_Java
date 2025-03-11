package controller;

import dao.UserDAO;
import exceptions.DatabaseException;
import model.User;

public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(String username, String password) throws DatabaseException {
        User user = userDAO.authenticate(username, password);
        return user != null;
    }

    public boolean isAdmin(String username, String password) throws DatabaseException {
        User user = userDAO.authenticate(username, password);
        return user != null && user.isAdmin();
    }
}
