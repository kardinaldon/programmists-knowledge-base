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

@ManagedBean
@RequestScoped
@Data
@Slf4j
public class LoginBean {


    protected String email;
    protected String password;

    private UserService userService = new UserService();
    private User user = new User ();

    protected FacesContext context = FacesContext.getCurrentInstance();
    protected FacesMessage message;

    private String path = context.getExternalContext().getRequestContextPath();
    private ExternalContext externalContext = context.getExternalContext();

    public void findUser (@Email @NotNull String email, @NotNull String password) throws InterruptedException {

       user = userService.findUserByEmail(email);

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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
