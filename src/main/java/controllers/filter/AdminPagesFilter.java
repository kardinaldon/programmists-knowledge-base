package controllers.filter;

import beans.session_bean.SessionBean;
import models.entity.user.User;
import service.UserService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(
        urlPatterns = "/administrator/*"
)
public class AdminPagesFilter implements Filter {

    private User user;
    private UserService userService = new UserService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("name") != null) {
            user = userService.findUserByEmail(session.getAttribute("name").toString());
            if ((user = userService.findUserByEmail(session.getAttribute("name").toString())) != null && (user.getSessionId().equals(session.getId()))) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.sendRedirect("../login_page.xhtml");
            }
        } else {
            httpServletResponse.sendRedirect("../login_page.xhtml");
        }


    }

    @Override
    public void destroy() {

    }
}
