package controller;

import repository.UserRepository;
import exceptions.DatabaseException;
import model.User;

public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public boolean login(String username, String password) throws DatabaseException {
        User user = userRepo.authenticate(username, password);
        return user != null;
    }

    public boolean isAdmin(String username, String password) throws DatabaseException {
        User user = userRepo.authenticate(username, password);
        return user != null && user.isAdmin();
    }
}
