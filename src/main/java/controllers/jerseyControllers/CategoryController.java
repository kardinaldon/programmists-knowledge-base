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
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategory () {
//        categoryList.addAll(categoryService.findAllCategory());
        return categoryService.findAllCategory();
    }

    @GET
    @Path("/tree")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Category> getCategoryTree (@QueryParam("id") int id) {
//        categoryList.addAll(categoryService.findAllCategory());
//        categoryList = categoryService.findAllCategory();

        return categoryService.findCategoryTree(id);
    }

    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category getCategoryById (@QueryParam("id") int id) {
        return categoryService.findCategoryById(id);
    }

    @GET
    @Path("/category/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category getByTitle (@PathParam("title") String title) {
        return categoryService.findCategoryByTitle(title);
    }

    @GET
    @Path("/key")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Category> findByKeywords (@QueryParam("key") String key) {
        return categoryService.findCategoryByKeyword(key);
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCategory (Category category) {
        categoryService.addCategoryInNestedSet(category);
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
