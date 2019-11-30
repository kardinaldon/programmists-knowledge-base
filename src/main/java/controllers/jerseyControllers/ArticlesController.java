package controllers.jerseyControllers;

import models.entity.Article;
import models.dto.ArticleModelForJsonOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ArticleService;
import service.CategoryService;
import service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Path("/article")
public class ArticlesController {

    private ArticleService articleService = new ArticleService();
    private Article article = new Article();
    private List<Article> articleList = new ArrayList<>();
    private ArticleModelForJsonOut articleModelForJsonOut = new ArticleModelForJsonOut();
    private List <ArticleModelForJsonOut> articleModelForJsonOutList = new ArrayList<>();
    private UserService userService = new UserService();

    private static final Logger logger = LoggerFactory.getLogger(ArticlesController.class);

    //получить все статьи http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List <ArticleModelForJsonOut> getAllArticles() {
        articleList = articleService.findAllArticles();
        for (Article article: articleList) {
            articleModelForJsonOut = new ArticleModelForJsonOut();
            articleModelForJsonOut.setArticleId(article.getArticleId());
            articleModelForJsonOut.setTitle(article.getTitle());
            articleModelForJsonOut.setSmallDescription(article.getSmallDescription());
            articleModelForJsonOut.setDescription(article.getDescription());
            articleModelForJsonOut.setCategoryName(article.getCategory().getTitle());
            articleModelForJsonOut.setUserName(article.getUser().getEmail());
            articleModelForJsonOut.setDateOfCreation(String.valueOf(article.getDateOfCreation()));
            articleModelForJsonOutList.add(articleModelForJsonOut);
        }
        logger.info("RESTfull Service running /article/all");
        return articleModelForJsonOutList;
    }

    //получить определенное кол-во статей с определенной
    // http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/part?start=0&limit=10
    @GET
    @Path("/part")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ArticleModelForJsonOut> getArticleOnId(@QueryParam("start") int start,
                                  @QueryParam("limit") int limit) {
        articleList = articleService.selectArticlesWithLimit(start,limit);
        for (Article article: articleList) {
            articleModelForJsonOut = new ArticleModelForJsonOut();
            articleModelForJsonOut.setArticleId(article.getArticleId());
            articleModelForJsonOut.setTitle(article.getTitle());
            articleModelForJsonOut.setSmallDescription(article.getSmallDescription());
            articleModelForJsonOut.setDescription(article.getDescription());
            articleModelForJsonOut.setCategoryName(article.getCategory().getTitle());
            articleModelForJsonOut.setUserName(article.getUser().getEmail());
            articleModelForJsonOut.setDateOfCreation(String.valueOf(article.getDateOfCreation()));
            articleModelForJsonOutList.add(articleModelForJsonOut);
        }
        return articleModelForJsonOutList;
    }

    //получить статью по id
    // http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article?id=1   - получить статью с id 1
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Article getArticleOnId(@QueryParam("id") int id) {
        return articleService.findArticleWithId(id);
    }

    //создать новую статью http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/new
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createNewArticle (Article article) {
        article.setDateOfCreation(LocalDateTime.now(Clock.system(ZoneId.of("Europe/Moscow"))));
        article.setUser(userService.findUserById(1));
        articleService.saveArticle(article);
    }
}
