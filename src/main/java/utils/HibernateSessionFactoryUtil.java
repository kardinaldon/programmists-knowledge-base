package utils;

import models.entity.Article;
import models.entity.Category;
import models.entity.GeneratedValues;
import models.entity.LetterTemplate;
import models.entity.user.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;
    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Article.class);
                configuration.addAnnotatedClass(Category.class);
                configuration.addAnnotatedClass(GeneratedValues.class);
                configuration.addAnnotatedClass(LetterTemplate.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Session Factory exception" + e);
            }
        }
        return sessionFactory;
    }
}
