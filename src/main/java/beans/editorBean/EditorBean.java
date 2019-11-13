package beans.editorBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import models.Article;
import models.Category;
import models.user.User;
import service.ArticleService;
import service.CategoryService;

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

    public void createArticle (@NotNull String headline,
                               @NotNull String text) {
        article.setTitle(headline);
        category.setTitle("Test category");
        article.setCategory(category);
        article.setDescription(text);
        article.setSmallDescription("Test Small description");
        Clock clock = Clock.system(ZoneId.of("Europe/Moscow"));
        article.setDateOfCreation(LocalDateTime.now(clock));
        user.setEmail("kardinaldon@ya.ru");
        article.setUser(user);
        articleService.saveArticle(article);

    }

}
