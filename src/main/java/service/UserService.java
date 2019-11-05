package service;

import dao.UserDAO;
import models.user.User;

import javax.transaction.Transactional;
import java.util.List;

//@Transactional
public class UserService {
    private UserDAO userDAO = new UserDAO();

    public UserService() {
    }

    public User findUserById(int id) {
        return userDAO.findById(id);
    }

    public List<User> findUserByEmail(String email) throws InterruptedException {
        return userDAO.findByName(email);
    }

    @Transactional
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
