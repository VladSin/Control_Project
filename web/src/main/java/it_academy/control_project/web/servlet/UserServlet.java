package it_academy.control_project.web.servlet;

import it_academy.control_project.data.Applicant;
import it_academy.control_project.data.User;
import it_academy.control_project.service.IApplicantService;
import it_academy.control_project.service.impl.DefaultApplicantService;
import it_academy.control_project.service.impl.DefaultUserService;
import it_academy.control_project.service.IUserService;
import it_academy.control_project.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private IUserService userService = DefaultUserService.getInstance();
    private static final Logger log = LoggerFactory.getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getUser();
        request.setAttribute("users", users);
        WebUtils.forward("user", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        User user = new User(null, name, surname, phone, email);
        User saveUser = userService.saveUser(user);
        log.info("user created:{} at {}", saveUser.getId(), LocalDateTime.now());
        request.getSession().setAttribute("userId", saveUser.getId());

        WebUtils.redirect("/faculty", request, response);
    }
}
