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
    private Article article;
    private List<Article> articleList;
    private ArticleModelForJsonOut articleModelForJsonOut;
    private List <ArticleModelForJsonOut> articleModelForJsonOutList;
    private UserService userService = new UserService();
    private CategoryService categoryService = new CategoryService();

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
        System.out.println("RESTfull Service running /article/all");
        return articleModelForJsonOutList;
    }

    //получить статью по id
    // http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/get_by_id?id=1   - получить статью с id 1
    @GET
    @Path("/get_by_id")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Article getArticleOnId(@QueryParam("id") int id) {
        System.out.println("RESTful Service running article/getwitid: "+id);
        return articleService.findArticleWithId(id);
    }

    //создать новую статью http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/newarticle
    @POST
    @Path("/new_article")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createNewArticle (Article article) {
        article.setDateOfCreation(LocalDateTime.now(Clock.system(ZoneId.of("Europe/Moscow"))));
        article.setUser(userService.findUserById(1));
        article.setCategory(categoryService.findCategoryById(1));
        articleService.saveArticle(article);
        System.out.println("RESTful Service running /newproduct "+article.getArticleId()+" "+article.getTitle()+" "+article.getCategory().getTitle()+" "+article.getUser());
    }

}
