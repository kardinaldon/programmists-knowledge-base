package beans.session_bean;

import beans.loginBean.LoginBean;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;


@ManagedBean(eager = true)
@SessionScoped
@Slf4j
@Data
public class SessionBean implements Serializable {

    @Inject
    LoginBean loginBean;

    private static final long serialVersionUID = 4441192489298102578L;

    private String sessionId = loginBean.getSessionId();

    private String email = loginBean.getEmail();




}
