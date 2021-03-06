package controllers.jerseyControllers;

import models.entity.Article;
import models.dto.ArticleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ArticleService;
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
    private ArticleDto articleDto = new ArticleDto();
    private List <ArticleDto> articleDtoList = new ArrayList<>();
    private UserService userService = new UserService();

    private static final Logger logger = LoggerFactory.getLogger(ArticlesController.class);

    //получить все статьи http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/all
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List <ArticleDto> getAllArticles() {
        articleList = articleService.findAllArticles();
        for (Article article: articleList) {
            articleDto = new ArticleDto();
            articleDto.setArticleId(article.getArticleId());
            articleDto.setTitle(article.getTitle());
            articleDto.setSmallDescription(article.getSmallDescription());
            articleDto.setDescription(article.getDescription());
            articleDto.setCategoryName(article.getCategory().getTitle());
            articleDto.setUserName(article.getUser().getEmail());
            articleDto.setDateOfCreation(article.getDateOfCreation());
            articleDtoList.add(articleDto);
        }
        logger.info("RESTfull Service running /article/all");
        return articleDtoList;
    }

    //получить определенное кол-во статей с определенной
    // http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/part?start=0&limit=10
    @GET
    @Path("/part")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ArticleDto> getArticleOnId(@QueryParam("start") int start,
                                           @QueryParam("limit") int limit) {
        articleList = articleService.selectArticlesWithLimit(start,limit);
        for (Article article: articleList) {
            articleDto = new ArticleDto();
            articleDto.setArticleId(article.getArticleId());
            articleDto.setTitle(article.getTitle());
            articleDto.setSmallDescription(article.getSmallDescription());
            articleDto.setDescription(article.getDescription());
            articleDto.setCategoryName(article.getCategory().getTitle());
            articleDto.setUserName(article.getUser().getEmail());
            articleDto.setDateOfCreation(article.getDateOfCreation());
            articleDtoList.add(articleDto);
        }
        return articleDtoList;
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

    //получить общее кол-во статей в БД http://localhost:8080/com_programmists_knowledge_base_1_war/rest/article/count
    @GET
    @Path("/count")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public Long getCountOfArticles() {
        return articleService.selectCountOfArticlesFromAnyCategories();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteArticle(@PathParam("id") int id) {
        articleService.deleteArticle(id);
    }

}
