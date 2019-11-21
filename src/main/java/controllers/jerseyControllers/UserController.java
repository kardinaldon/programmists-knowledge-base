package controllers.jerseyControllers;

import models.entity.user.User;
import service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
public class UserController {

    private User user;
    private UserService userService;

    // http://localhost:8080/com_programmists_knowledge_base_1_war/rest/user/get_all
    @GET
    @Path("/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers () {
        return userService.findAllUsers();
    }


    // http://localhost:8080/com_programmists_knowledge_base_1_war/rest/user/get_by_id?id=
    @GET
    @Path("/get_by_id")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User getById (@QueryParam("id") int id) {
        return userService.findUserById(id);
    }

    // http://localhost:8080/com_programmists_knowledge_base_1_war/rest/user/get_by_email?email=
    @GET
    @Path("/get_by_email")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User getByEmail (@QueryParam("email") String email){
        return userService.findUserByEmail(email);
    }

    // http://localhost:8080/com_programmists_knowledge_base_1_war/rest/user/update_user
    @PUT
    @Path("/update_user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser (User user) {
        userService.updateUser(user);
    }

    // http://localhost:8080/com_programmists_knowledge_base_1_war/rest/user/create_user
    @POST
    @Path("/create_user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser (User user) {
        userService.createUser(user);
    }

    // http://localhost:8080/com_programmists_knowledge_base_1_war/rest/user/delete_user
    @DELETE
    @Path("/delete_user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUser (User user) {
        userService.deleteUser(user);
    }
}
