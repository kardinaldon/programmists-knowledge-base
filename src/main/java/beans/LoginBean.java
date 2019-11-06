package beans;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import models.user.User;
import service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@ManagedBean
@RequestScoped
@Data
@Slf4j
public class LoginBean {


    String email;
    String password;

    UserService userService = new UserService();

    FacesContext context = FacesContext.getCurrentInstance();
    FacesMessage message;
    String path = context.getExternalContext().getRequestContextPath();
    ExternalContext externalContext = context.getExternalContext();

    public void findUser (@Email @NotNull String email, @NotNull String password) throws InterruptedException {

        List<User> users = userService.findUserByEmail(email);
        for (User user: users) {
            if (!email.isEmpty() & !password.isEmpty()) {
                if(user != null && user.getPassword().equals(password)) {
                    addMessage(false, "Вход выполнен");
                    log.info("login as "+email);
                    try {
                        externalContext.redirect(path + "/secured/index.html");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    addMessage(true, "Пользователь с такими учетными данными не найден");
                    log.error("wrong username or password");
                }
            } else {
                addMessage(true, "ВВедите ваш логин и пароль");
                log.error("one of the fields in JSF GUI is empty");
            }
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
