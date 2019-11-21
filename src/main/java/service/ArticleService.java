package service;

import dao.ArticleDAO;
import models.entity.Article;

import java.util.List;

public class ArticleService {
    private ArticleDAO articleDAO = new ArticleDAO();

    public ArticleService() {
    }


    public Article findArticleWithId (int id) {
        return articleDAO.findById(id);
    }

    public void saveArticle(Article article) {
        articleDAO.save(article);
    }

    public void deleteArticle(Article article) {
        articleDAO.delete(article);
    }

    public void deleteAllArticle() {
        articleDAO.deleteAll();
    }

    public void updateArticle(Article article) {
        articleDAO.update(article);
    }

    public List<Article> findAllArticles() {
        return articleDAO.findAll();
    }
}
