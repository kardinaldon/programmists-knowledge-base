package beans;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import models.user.RoleEnum;
import models.user.StatusEnum;
import models.user.User;
import service.RegisterService;
import service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@ManagedBean
@RequestScoped
@Data
@Slf4j
public class RegisterBean {

    String email;
    String password;

    private UserService userService = new UserService();
    private User user= new User();

    FacesContext context = FacesContext.getCurrentInstance();
    Object sessionMap = context.getExternalContext().getSessionMap().get("JSESSIONID");
    FacesMessage message;
    private String path = context.getExternalContext().getRequestContextPath();
    private ExternalContext externalContext = context.getExternalContext();
    private RegisterService registerService = new RegisterService();

    public void createUser (@Email @NotNull String email, @NotNull String password) {
        System.out.println((String) sessionMap.toString());
        if (!email.isEmpty() & !password.isEmpty()) {
            try {
                if (userService.findUserByEmail(email) != null) {
                    addMessage(true, "Пользователь с такими email уже создан");
                    log.error("wrong username or password");
                } else {
                    registerService.startRegistration(email,password);
                    addMessage(false, "На ваш электронный адрес отправлено письмо, перейдите по ссылке из письма для завершения регистрации");
                    log.info("User " + email + "created");
                    try {
                        TimeUnit.SECONDS.sleep(10);
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
