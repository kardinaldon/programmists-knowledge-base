package dao;

import models.entity.Category;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import utils.HibernateSessionFactoryUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public Category findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Category category = session.get(Category.class, id);
        session.close();
        return category;
    }

    public Category findByTitle(String title) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Category category = session.byNaturalId(Category.class).using("title", title).load();
        session.close();
        return category;
    }

    public List<Category> findByParentId(int parentId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Category> categoryList = (List<Category>)  session.createQuery("From models.entity.Category where parentId="+parentId).list();
        session.close();
        return categoryList;
    }

    public void addCategoryInNestedSet(int parentId, Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if(parentId == 0) {
            try {
                Query query = session.createSQLQuery("select max (rght) FROM categories");
                int right = (int) query.getSingleResult();
                category.setLeft(right+1);
                category.setRight(right+2);
                save(category);
                transaction.commit();
            } catch (Exception ex) {
                transaction.rollback();
                throw new RuntimeException(ex);
            }
        } else {
            try {
                Category parentCategory = findById(parentId);
                Query query = session.createQuery("update models.entity.Category set lft = lft + 2 where lft >:right");
                query.setParameter("right", parentCategory.getRight());
                query.executeUpdate();
                query = session.createQuery("update models.entity.Category set rght = rght + 2 where rght >=:right");
                query.setParameter("right", parentCategory.getRight());
                query.executeUpdate();
                category.setLeft(parentCategory.getRight());
                category.setRight(parentCategory.getRight()+1);
                save(category);
                transaction.commit();
            } catch (Exception ex) {
                transaction.rollback();
                throw new RuntimeException(ex);
            }
        }


    }

    public void deleteCategoryFromNestedSet (Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //если узел не имеет потомков
        if (category.getRight()-category.getLeft()==1) {
            try {
                Query query = session.createQuery("update models.entity.Category set lft = lft - 2 where lft >:left");
                query.setParameter("left", category.getLeft());
                query.executeUpdate();
                query = session.createQuery("update models.entity.Category set rght = rght - 2 where rght >:right");
                query.setParameter("right", category.getRight());
                query.executeUpdate();
                delete(category);
                transaction.commit();
            } catch (Exception ex) {
                transaction.rollback();
                throw new RuntimeException(ex);
            }
        } else {
            try {

                Query query = session.createQuery("update models.entity.Category set lft = lft - 1 where lft >:left and rght <:right");
                query.setParameter("left", category.getLeft());
                query.setParameter("right", category.getRight());

                query = session.createQuery("update models.entity.Category set rght = rght - 1 where lft >:left and rght <:right");
                query.setParameter("left", category.getLeft());
                query.setParameter("right", category.getRight());
                query.executeUpdate();

                query = session.createQuery("update models.entity.Category set lft = lft - 2 where lft >:right");
                query.setParameter("right", category.getRight());
                query.executeUpdate();

                query = session.createQuery("update models.entity.Category set rght = rght - 2 where rght >:right");
                query.setParameter("right", category.getRight());
                query.executeUpdate();
                delete(category);
                transaction.commit();
            } catch (Exception ex) {
                transaction.rollback();
                throw new RuntimeException(ex);
            }
        }
        session.close();
    }

    public List<Category> findByKeyword (String keyword) throws InterruptedException {
        List<Category> categories = null;
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            FullTextSession fullTextSession = Search.getFullTextSession(session);
            fullTextSession.createIndexer().startAndWait();

            QueryBuilder qb = fullTextSession.getSearchFactory()
                    .buildQueryBuilder().forEntity(Category.class).get();

            org.apache.lucene.search.Query lucenceQuery =
                    qb.keyword().onFields("title", "description")
                            .matching(keyword).createQuery();

            @SuppressWarnings("unchecked")
            Query<Category> query = fullTextSession.createFullTextQuery(lucenceQuery,
                    Category.class);
            categories = query.getResultList();

            transaction.commit();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return categories;
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
        session.createQuery("DELETE FROM models.entity.Category").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Category> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Category> categoryList = (List<Category>)  session.createQuery("From models.entity.Category").setCacheable(true).list();
        session.close();
        return categoryList;
    }
}
