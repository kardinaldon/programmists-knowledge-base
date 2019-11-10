package controllers;

import lombok.extern.slf4j.Slf4j;
import models.user.StatusEnum;
import models.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.GeneratedValueService;
import service.UserService;

import javax.faces.context.FacesContext;
import javax.ws.rs.*;

@Path("/email_verification_page.xhtml")
@Slf4j
public class EmailVerificationController {

    private static final Logger logger = LoggerFactory.getLogger(ArticlesController.class);
    private GeneratedValueService generatedValueService = new GeneratedValueService();
    private UserService userService = new UserService();

    private FacesContext context = FacesContext.getCurrentInstance();
    //private String path = context.getExternalContext().getRequestContextPath();
    //private ExternalContext externalContext = context.getExternalContext();


    // http://localhost:8080/com_programmists_knowledge_base_1_war/email_verification_page.xhtml?id=1&email=
    @GET
    public void getArticleOnId(
            @QueryParam("id") String id,
            @QueryParam ("email") String email) {
        try {
            if(generatedValueService.findGeneratedValuesByValue(id) != null) {
                User user;
                user = userService.findUserByEmail(email);
                user.setStatusEnum(StatusEnum.ACTIVE);
                userService.updateUser(user);
                //externalContext.redirect(path + "/");
            } else {
                log.warn("Попытка верификации по неправильной ссылке");
            }

        } catch (InterruptedException e ) {
            e.printStackTrace();
        }


    }
}
