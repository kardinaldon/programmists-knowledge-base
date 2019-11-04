package controllers;

import models.User;
import service.UserService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UsersController {

    private UserService userService;
    private Boolean answer;

//    find user by name http://localhost:8080/com_programmists_knowledge_base_1_war/users/get_by_name
    @GET
    @Path("/find_by_name")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean getArticleOnId(String name, String password) {
        User user = userService.findUserByName(name);
        if (user != null & password.equals(user.getPassword())) {
            answer = true;
        } else {
            answer = false;
        }
        return answer;
    }
}
