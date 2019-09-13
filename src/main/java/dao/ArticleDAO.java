package dao;

import models.Article;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.databaseutils.HibernateSessionFactoryUtil;

import java.util.List;

public class ArticleDAO {
    public Article findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Article article = session.get(Article.class, id);
        session.close();
        return article;
    }


    public void save(Article article) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(article);
        transaction.commit();
        session.close();
    }

    public void update(Article article) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(article);
        transaction.commit();
        session.close();
    }

    public void delete(Article article) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(article);
        transaction.commit();
        session.close();
    }

    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM models.Article").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public List<Article> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Article> articleList = (List<Article>)  session.createQuery("From models.Article").list();
        session.close();
        return articleList;
    }
}
