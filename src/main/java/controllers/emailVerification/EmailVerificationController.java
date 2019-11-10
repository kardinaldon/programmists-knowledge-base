package controllers.emailVerification;

import controllers.ArticlesController;
import lombok.extern.slf4j.Slf4j;
import models.user.StatusEnum;
import models.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.GeneratedValueService;
import service.UserService;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Path("/email_verification_page.xhtml")
@Slf4j
public class EmailVerificationController {

    private static final Logger logger = LoggerFactory.getLogger(ArticlesController.class);
    private GeneratedValueService generatedValueService = new GeneratedValueService();
    private UserService userService = new UserService();
    private User user;

    // http://localhost:8080/com_programmists_knowledge_base_1_war/email_verification_page.xhtml?id=1&email=
    @GET
    public void getVerification(
            @QueryParam("id") String id,
            @QueryParam ("email") String email) {
        try {
            if(generatedValueService.findGeneratedValuesByValue(id) != null) {
                user = userService.findUserByEmail(email);
                user.setStatusEnum(StatusEnum.ACTIVE);
                userService.updateUser(user);
            } else {
                log.warn("Попытка верификации по неправильной ссылке");
            }

        } catch (InterruptedException e ) {
            e.printStackTrace();
        }


    }
}
