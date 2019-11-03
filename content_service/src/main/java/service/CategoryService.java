package service;

import dao.CategoryDAO;
import models.Category;

import java.util.List;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();

    public CategoryService() {
    }

    public Category findCategoryWitId(int id) {
        return categoryDAO.findById(id);
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
