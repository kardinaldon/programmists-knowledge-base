package controllers;

import models.Article;
import models.modelsForRestApi.ArticleModelForJsonOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ArticleService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/article")
public class ArticlesController {

    private ArticleService articleService = new ArticleService();
    private Article article;
    private List<Article> articleList;
    private ArticleModelForJsonOut articleModelForJsonOut;
    private List <ArticleModelForJsonOut> articleModelForJsonOutList;

    private static final Logger logger = LoggerFactory.getLogger(ArticlesController.class);

    //получить все статьи http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List <ArticleModelForJsonOut> getAllArticles() {
        articleList = articleService.findAllArticles();
        articleModelForJsonOutList = new ArrayList<>();
        for (Article article: articleList) {
            articleModelForJsonOut = new ArticleModelForJsonOut();
            articleModelForJsonOut.setArticleId(article.getId());
            articleModelForJsonOut.setTitle(article.getTitle());
            articleModelForJsonOut.setSmallDescription(article.getSmallDescription());
            articleModelForJsonOut.setDescription(article.getDescription());
            articleModelForJsonOut.setCategoryName(article.getCategory().getTitle());
            articleModelForJsonOut.setUserName(article.getUser().getName());
            articleModelForJsonOut.setDateOfCreation(String.valueOf(article.getDateOfCreation()));
            articleModelForJsonOutList.add(articleModelForJsonOut);
        }
        logger.info("RESTful Service running /article/all");
        System.out.println("RESTful Service running /article/all");
        return articleModelForJsonOutList;
    }

    //получить статью по id
    // http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/getwitid?id=1   - получить статью с id 1
    @GET
    @Path("/getwitid")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Article getArticleOnId(@QueryParam("id") int id) {
        article = articleService.findArticleWithId(id);
        System.out.println("RESTful Service running article/getwitid: "+id);
        return article;
    }

    //создать новую статью http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/newarticle
    @POST
    @Path("/newarticle")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Article createNewArticle (Article article) {
        articleService.saveArticle(article);
        System.out.println("RESTful Service running /newproduct "+article.getId()+" "+article.getTitle()+" "+article.getCategory().getTitle()+" "+article.getUser().getName());
        return article;
    }

}
