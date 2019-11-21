package dao;

import models.entity.GeneratedValues;
import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateSessionFactoryUtil;


public class GeneratedValueDAO {

    public GeneratedValues findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        GeneratedValues generatedValues = session.get(GeneratedValues.class, id);
        session.close();
        return generatedValues;
    }

    public GeneratedValues findByValue(String value) throws InterruptedException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        GeneratedValues generatedValues = session.byNaturalId(GeneratedValues.class).using("generatedValue", value).load();
        session.close();
        return generatedValues;
    }

    public void save(GeneratedValues generatedValues) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(generatedValues);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException(ex);
        }
        session.close();

    }

    public void delete(GeneratedValues generatedValues) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(generatedValues);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException(ex);
        }
        session.close();
    }

}
