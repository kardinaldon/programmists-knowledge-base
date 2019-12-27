package service;

import dao.UserDAO;
import models.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

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

    public void deleteUser(int userId) {
        User user = userDAO.findById(userId);
        userDAO.delete(user);
    }

    public void deleteAllUsers() {
        userDAO.deleteAll();
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public List<User> findAllUsers() {
        try {
            List<User> userList = userDAO.findAll();
            return userList;
        } catch (Exception e) {
            LOGGER.info(e.getCause().toString());
            return Collections.EMPTY_LIST;
        }
    }
}
