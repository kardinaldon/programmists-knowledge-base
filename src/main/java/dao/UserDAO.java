package dao;

import models.user.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;


import java.util.List;

public class UserDAO {
    public User findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public User findByName(String email) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = session.byNaturalId(User.class).using("email", email).load();
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
        session.createQuery("DELETE FROM models.user.User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public List<User> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<User> userList = session.createQuery("From models.user.User").list();
        session.close();
        return userList;
    }
}
