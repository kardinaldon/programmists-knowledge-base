import models.Article;
import models.Category;
import models.User;
import services.ArticleService;
import services.CategoryService;
import services.UserService;

public class Main {


    public static void main(String[] args) {

        //Тестово создать пользователя
        UserService userService = new UserService();
        User user = new User ("Ivan","123@mail.ru", "123456");
        userService.createUser(user);

        //Тестово создать категорию
        CategoryService categoryService = new CategoryService();
        Category category = new Category("Second Test Category", "desc2");
        categoryService.saveCategory(category);

        //Тестово содать статью
        ArticleService articleService = new ArticleService();
        Article article =new Article();
        article.setTitle("Article title");
        article.setDescription("Article Description");
        article.setCategory(category);
        article.setUser(user);
        articleService.saveArticle(article);
    }
}
