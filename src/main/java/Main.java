import models.Article;
import models.Category;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.ArticleService;
import services.CategoryService;
import services.UserService;

import java.time.LocalDateTime;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        //Тестово создать пользователя
        UserService userService = new UserService();
        User user = new User ("Fedor23g54s241","2fd5573386g45s253044812024439711366amail.ru", "f2d443555g2s4240263g7591841107gg6633sab");
        userService.createUser(user);

        //Тестово создать категорию
        CategoryService categoryService = new CategoryService();
        Category category = new Category("Some T4535676414982044g16s73525210gg33 rrr", "rsg1055149636s3g244502817627544fr");
        categoryService.saveCategory(category);

        //Тестово содать статью
        ArticleService articleService = new ArticleService();
        Article article =new Article();
        article.setTitle("Article Tit375854414160582139ss3420g6425637ggsdf");
        article.setSmallDescription("Small Arti1140cl73e sDes6c5224071g59852543r66i4433bcvb");
        article.setDescription("Article Descri101646p73t2256si8524410g5957on443gb");
        article.setDateOfCreation(LocalDateTime.now());
        article.setCategory(category);
        article.setUser(user);
        articleService.saveArticle(article);


        logger.info("hello");
    }
}
