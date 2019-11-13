package controllers.listener;

import beans.loginBean.LoginBean;
import beans.session_bean.SessionBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

//@WebServlet(urlPatterns = "/login_page.xhtml",
//        name = "administrator_pages_servlet",
//        displayName = "administrator_controller",
//        description = "controller for handling administrative interface requests")

@WebListener (value = "/login_page.xhtml")
public class LoginController implements HttpSessionListener, HttpSessionAttributeListener, Serializable {


    private static final long serialVersionUID = -9009395198060986786L;

    @Inject
    private LoginBean loginBean;

    @Inject
    private SessionBean sessionBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        String cookiesArrayString = Arrays.toString(cookies);
        System.out.println(cookiesArrayString);
        List <String> sessionAtrbuts = (List<String>) session.getAttributeNames();
        for (String sessionAtr:sessionAtrbuts) {
            System.out.println(sessionAtr);
        }

    }
}
