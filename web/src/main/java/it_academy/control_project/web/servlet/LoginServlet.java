package it_academy.control_project.web.servlet;

import it_academy.control_project.dao.impl.DefaultAuthUserStorage;
import it_academy.control_project.data.AuthorizationUser;
import it_academy.control_project.service.IAuthUserService;
import it_academy.control_project.service.impl.DefaultAuthUserService;
import it_academy.control_project.service.impl.DefaultSecurityService;
import it_academy.control_project.service.ISecurityService;
import it_academy.control_project.web.WebUtils;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import org.slf4j.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private ISecurityService securityService = DefaultSecurityService.getInstance();
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

        WebUtils.forward("login", request, response);
        AuthorizationUser authorizationUser = securityService.login(login, password);
        if (authorizationUser == null) {
            request.setAttribute("error", "login or password invalid");
            WebUtils.forward("login", request, response);
            return;
        }
        log.info("user {} logged", authorizationUser.getLogin());
        request.getSession().setAttribute("authUser", authorizationUser);
        WebUtils.redirect("/student", request, response);
    }
}
