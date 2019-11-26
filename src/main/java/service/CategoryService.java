package service;

import dao.CategoryDAO;
import models.entity.Category;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();
    private Category category = new Category();
    private List<Category> categoryList = new ArrayList<>();

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

    public List<Category> findByParentId (int parentId) {
        return categoryDAO.findByParentId(parentId);
    }

    public List<Category> findCategoryTree (int parentId) {

        for (Category category1 : categoryDAO.findByParentId(parentId)) {

            List<Category> childCategories = categoryDAO.findByParentId(category1.getCategoryId());
            if(!childCategories.isEmpty()) {
                categoryList.add(category1);
                findCategoryTree(category1.getCategoryId());
            } else {
                categoryList.add(category1);
            }
        }
        return categoryList;
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
