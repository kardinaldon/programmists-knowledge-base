import dao.CategoryDAO;
import models.entity.Category;
import service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class ForTest {

    CategoryService categoryService = new CategoryService();
    CategoryDAO categoryDAO = new CategoryDAO();
    Category category = new Category();
    List<Category> outCategories = new ArrayList<>();

    public List<Category> findCategoryTree (int parentId) {

        for (Category category1 : categoryDAO.findByParentId(parentId)) {

            List<Category> childCategories = categoryDAO.findByParentId(category1.getCategoryId());
            if(!childCategories.isEmpty()) {
                outCategories.add(category1);
                findCategoryTree(category1.getCategoryId());
            } else {
                outCategories.add(category1);
            }
        }
        return outCategories;
    }


}
