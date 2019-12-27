package dao;

import models.entity.user.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateSessionFactoryUtil;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

    private User user;

    public User findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            user = session.get(User.class, id);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.getCause();
        }
        session.close();
        return user;
    }

    public User findByName(String email) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            user = session.byNaturalId(User.class).using("email", email).load();
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.getCause();
        }

        session.close();
        return user;
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException(ex);
        }
        session.close();

    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException(ex);
        }
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(user);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException(ex);
        }
        session.close();
    }

    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM models.entity.user.User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            List<User> userList =  session.createQuery("FROM models.entity.user.User").list();
            session.close();
            return userList;
        } catch (Exception e) {
            LOGGER.info(e.getCause().toString());
            return Collections.EMPTY_LIST;
        }
    }
}
