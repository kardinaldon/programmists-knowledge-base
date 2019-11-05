package service;

import dao.UserDAO;
import models.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class UserService {
    private UserDAO userDAO = new UserDAO();

    public UserService() {
    }

    public User findUserById(int id) {
        return userDAO.findById(id);
    }

    public List<User> findUserByName(String name) throws InterruptedException {
        return userDAO.findByName(name);
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
