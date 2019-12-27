package controllers.jerseyControllers;

import models.dto.UserDto;
import models.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class UserController {

    private User user;
    private UserDto userDto;
    private UserService userService = new UserService();
    private List<User> userList = new ArrayList<>();
    private List<UserDto> userDtoList = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllUsers () {
        try {
            userList = userService.findAllUsers();
            for (User user: userList) {
                userDto = new UserDto();
                userDto.setUserId(user.getUserId());
                userDto.setEmail(user.getEmail());
                userDto.setRole(user.getRole());
                userDto.setStatusEnum(user.getStatusEnum());
                userDtoList.add(userDto);
            }
            return Response.ok().entity(new GenericEntity<List<UserDto>>(userDtoList){}).build();
        } catch (Exception e) {
            LOGGER.info(e.getCause().toString());
            return Response.noContent().build();
        }

    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User getById (@PathParam("id") int id) {
        return userService.findUserById(id);
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User getByEmail (@PathParam("email") String email){
        return userService.findUserByEmail(email);
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser (User user) {
        userService.updateUser(user);
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser (User user) {

        userService.createUser(user);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser (@PathParam("id") int id) {
        try {
            userService.deleteUser(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
