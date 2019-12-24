package service;

import dao.ArticleDAO;
import models.entity.Article;
import models.entity.Category;

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

    public void deleteArticle(int articleId) {
        Article article = articleDAO.findById(articleId);
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

    public List<Article> selectArticlesWithLimit (int start, int limit) {
        return articleDAO.selectArticlesWithLimit(start,limit);
    }

    public Long selectCountOfArticlesFromAnyCategories() {
        return articleDAO.selectCountOfArticlesFromAnyCategories();
    }

    public Long selectCountOfArticlesFromCertainCategory(Category category) {
        return articleDAO.selectCountOfArticlesFromCertainCategory(category);
    }
}
