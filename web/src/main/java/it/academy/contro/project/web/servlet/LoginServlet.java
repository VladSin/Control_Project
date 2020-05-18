package it.academy.contro.project.web.servlet;

import it.academy.control.project.data.AuthorizationUser;
import it.academy.control.project.service.impl.DefaultSecurityService;
import it.academy.control.project.service.SecurityService;
import it.academy.contro.project.web.WebUtils;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private SecurityService securityService = DefaultSecurityService.getInstance();

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object authorizationUser = request.getSession().getAttribute("authorizationUser");
        if (authorizationUser == null){
            WebUtils.forward("login", request, response);
            return;
        }
        WebUtils.redirect("/user", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        AuthorizationUser authorizationUser = securityService.login(login, password);
        if (authorizationUser == null) {
            request.setAttribute("error", "login or password invalid");
            WebUtils.forward("login", request, response);
            return;
        }

        log.info("authorizationUser {} logged", authorizationUser.getLogin());
        request.getSession().setAttribute("authorizationUser", authorizationUser);

        if (authorizationUser.getId().equals(1L)){
            WebUtils.redirect("/user", request, response);
        } else {
            WebUtils.redirect("/teacher", request, response);
        }
    }
}
