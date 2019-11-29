package beans.session_bean;

import beans.loginBean.LoginBean;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;


@Named
@SessionScoped
@Slf4j
@Data
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 4441192489298102578L;

//    private FacesContext context = FacesContext.getCurrentInstance();
//    private ExternalContext externalContext = context.getExternalContext();
//    private HttpSession session = (HttpSession) externalContext.getSession(true);
    private String sessionId;

}
