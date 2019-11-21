package dao;

import models.entity.user.SessionEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class SessionDAO {

    public SessionEntity findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        SessionEntity sessionEntity = session.get(SessionEntity.class, id);
        session.close();
        return sessionEntity;
    }

    public SessionEntity findByValue (String value) throws InterruptedException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        SessionEntity sessionEntity = session.byNaturalId(SessionEntity.class).using("sessionValue", value).load();
        session.close();
        return sessionEntity;
    }

    public void save(SessionEntity sessionEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(session);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException(ex);
        }
        session.close();

    }

    public void delete(SessionEntity sessionEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(sessionEntity);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException(ex);
        }
        session.close();
    }
}
