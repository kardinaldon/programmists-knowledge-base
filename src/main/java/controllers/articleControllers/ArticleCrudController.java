package controllers.articleControllers;

import models.Article;
import services.ArticleService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/article")
public class ArticleCrudController {

    private ArticleService articleService = new ArticleService();
    private List<Article> articleList;
    private Article article;

    //получить все статьи http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getAllArticles() {
        articleList = articleService.findAllArticles();
        System.out.println("RESTful Service running /article/all");
        return articleList;
    }

    //получить статью по id
    // http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/getwitid?id=1   - получить статью с id 1
    @GET
    @Path("/getwitid")
    @Produces(MediaType.APPLICATION_JSON)
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
