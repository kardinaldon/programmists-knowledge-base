package dao;

import models.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.databaseutils.HibernateSessionFactoryUtil;

import java.util.List;

public class CategoryDAO {
    public Category findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Category category = session.get(Category.class, id);
        session.close();
        return category;
    }


    public void save(Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(category);
        transaction.commit();
        session.close();
    }

    public void update(Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(category);
        transaction.commit();
        session.close();
    }

    public void delete(Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(category);
        transaction.commit();
        session.close();
    }

    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM models.Category").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public List<Category> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Category> categoryList = (List<Category>)  session.createQuery("From models.Category").list();
        session.close();
        return categoryList;
    }
}
