package service;

import dao.UserDAO;
import models.entity.user.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User findUserById(int id) {
        return userDAO.findById(id);
    }

    public User findUserByEmail(String email) {
        return userDAO.findByName(email);
    }

    public void createUser(User user) {
        userDAO.save(user);
    }

    public void deleteUser(User user) {
        userDAO.delete(user);
    }

    public void deleteAllUsers() {
        userDAO.deleteAll();
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }
}
