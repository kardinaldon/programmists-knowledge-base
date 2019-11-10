package dao;

import models.Article;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ArticleDAO {

    public Article findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Article article = session.get(Article.class, id);
        session.close();
        return article;
    }

    public Article findByNaturalId (String title) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Article article = session.get(Article.class, title);
        session.close();
        return article;
    }


    public List<Article> findByKeyword (String keyword) {
        List<Article> articles = null;
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            FullTextSession fullTextSession = Search.getFullTextSession(session);
            fullTextSession.createIndexer().startAndWait();

            QueryBuilder qb = fullTextSession.getSearchFactory()
                    .buildQueryBuilder().forEntity(Article.class).get();

            org.apache.lucene.search.Query lucenceQuery =
                    qb.keyword().onFields("title", "description", "smallDescription")
                            .matching(keyword).createQuery();

            @SuppressWarnings("unchecked")
            Query<Article> query = fullTextSession.createFullTextQuery(lucenceQuery,
                    Article.class);
            articles = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return articles;
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
