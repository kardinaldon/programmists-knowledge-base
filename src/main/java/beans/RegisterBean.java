package beans;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import models.user.RoleEnum;
import models.user.StatusEnum;
import models.user.User;
import service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@RequestScoped
@Data
@Slf4j
public class RegisterBean {

    String email;
    String password;

    UserService userService = new UserService();
    User user= new User();;

    FacesContext context = FacesContext.getCurrentInstance();
    FacesMessage message;
    String path = context.getExternalContext().getRequestContextPath();
    ExternalContext externalContext = context.getExternalContext();

    public void createUser (String email, String password) {
        if (!email.isEmpty() & !password.isEmpty()) {
            try {
                if (userService.findUserByEmail(email).size() != 0) {
                    addMessage(true, "Пользователь с такими email уже создан");
                    log.error("wrong username or password");
                } else {
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setRole(RoleEnum.USER);
                    user.setStatusEnum(StatusEnum.NOT_ACTIVE);
                    userService.createUser(user);
                    addMessage(false, "Пользователь создан");
                    log.info("User " + email + "created");
                    try {
                        externalContext.redirect(path + "/");
                    } catch (IOException e) {
                        e.printStackTrace();
                        addMessage(true, "Ошибка при создании пользователя");
                        log.error("wrong username or password");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            addMessage(true, "Заполните все поля");
            log.error("one of the fields are empty");
        }

    }

    private void addMessage(boolean error, String detail) {
        if (error) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Ошибка",
                    detail);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Вход выполнен",
                    detail);
        }
        context.addMessage(null, message);
        context.getPartialViewContext().getRenderIds().add("globalMessage");
    }
}
