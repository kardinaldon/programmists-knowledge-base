package service;

import dao.CategoryDAO;
import models.entity.Category;
import java.util.List;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();

    public List<Category> findCategoryByKeyword (String key) {

        List<Category> categories = null;

        try {
            categories = categoryDAO.findByKeyword(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category findCategoryById(int id) {
        return categoryDAO.findById(id);
    }

    public Category findCategoryByTitle (String title) {
        return categoryDAO.findByTitle(title);
    }

    public void saveCategory(Category category) {
        categoryDAO.save(category);
    }

    public void deleteCategory(Category category) {
        categoryDAO.delete(category);
    }

    public void deleteAllCategory() {
        categoryDAO.deleteAll();
    }

    public void updateCategory(Category category) {
        categoryDAO.update(category);
    }

    public List<Category> findAllCategory() {
        return categoryDAO.findAll();
    }
}
