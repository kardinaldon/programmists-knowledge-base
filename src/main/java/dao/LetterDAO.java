package dao;


import models.LetterTemplate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;



import java.util.List;

public class LetterDAO {

    public LetterTemplate findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        LetterTemplate letterTemplate = session.get(LetterTemplate.class, id);
        session.close();
        return letterTemplate;
    }

    public void save(LetterTemplate letterTemplate) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(letterTemplate);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException(ex);
        }
        session.close();

    }

    public void update(LetterTemplate letterTemplate) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(letterTemplate);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException(ex);
        }
        session.close();
    }

    public void delete(LetterTemplate letterTemplate) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(letterTemplate);
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
        session.createQuery("DELETE FROM models.LetterTemplate").executeUpdate();
        session.getTransaction().commit();
        session.flush();
        session.close();
    }

    public List<LetterTemplate> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<LetterTemplate> userList = (List<LetterTemplate>)  session.createQuery("From models.LetterTemplate").list();
        session.close();
        return userList;
    }
}
