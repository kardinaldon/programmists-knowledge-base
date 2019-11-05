package beans;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import models.User;
import service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@ManagedBean
@RequestScoped
@Data
@Slf4j
public class LoginBean {

    String userName;
    String password;

    FacesContext context = FacesContext.getCurrentInstance();
    FacesMessage message;

    public void findUser (@NotNull String userName, @NotNull String password) throws InterruptedException {
        UserService userService = new UserService();
        List<User> users = userService.findUserByName(userName);
        for (User user: users) {
            if (!userName.isEmpty() & !password.isEmpty()) {
                if(user != null && user.getPassword().equals(password)) {
                    addMessage(false, "Вход выполнен");
                    log.info("login as "+userName);
                    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    try {
                        ec.redirect("http://localhost:8080/com_programmists_knowledge_base_1_war/secured/index.html");
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
