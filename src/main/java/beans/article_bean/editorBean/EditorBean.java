package beans.article_bean.editorBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import models.entity.Article;
import models.entity.Category;
import models.entity.user.User;
import service.ArticleService;
import service.CategoryService;
import service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@ManagedBean
@RequestScoped
@Data
@Slf4j
public class EditorBean implements Serializable {

    private static final long serialVersionUID = -791063364347842035L;

    private String headline;
    private String text;

    private Article article = new Article();
    private ArticleService articleService;
    private CategoryService categoryService;
    private Category category = new Category();
    private User user = new User();
    private UserService userService = new UserService();

    public void createArticle (@NotNull String headline,
                               @NotNull String text) {
        article.setTitle(headline);
        category.setTitle("Test category");
        article.setCategory(category);
        article.setDescription(text);
        article.setSmallDescription("Test Small description");
        Clock clock = Clock.system(ZoneId.of("Europe/Moscow"));
        article.setDateOfCreation(LocalDateTime.now(clock));
        article.setUser(userService.findUserByEmail("kardinaldon@ya.ru"));
        articleService.saveArticle(article);

    }

}
