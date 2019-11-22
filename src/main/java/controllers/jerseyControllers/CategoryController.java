package controllers.jerseyControllers;

import models.entity.Category;
import service.CategoryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("/category")
public class CategoryController {

    private Category category = new Category();
    private List<Category> categoryList = new ArrayList<>();;
    private CategoryService categoryService = new CategoryService();

    @GET
    @Path("/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategory () {
//        categoryList.addAll(categoryService.findAllCategory());
        return categoryService.findAllCategory();
    }

    @GET
    @Path("/get_by_id")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category getCategoryById (int id) {
        return categoryService.findCategoryById(id);
    }

    @GET
    @Path("/get_by_title")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category getByTitle (String title) {
        return categoryService.findCategoryByTitle(title);
    }

    @POST
    @Path("/find_by_keyword")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Category> findByKeywords (String key) {
        return categoryService.findCategoryByKeyword(key);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCategory (Category category) {
        categoryService.saveCategory(category);
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCategory (Category category) {
        categoryService.updateCategory(category);
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteCategory (Category category) {
        categoryService.deleteCategory(category);
    }


}
