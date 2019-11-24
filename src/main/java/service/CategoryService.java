package service;

import dao.CategoryDAO;
import models.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();
    private Category category = new Category();
    private List<Category> outCategories = new ArrayList<>();

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
