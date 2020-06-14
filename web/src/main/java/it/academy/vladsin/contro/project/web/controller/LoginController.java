package it.academy.vladsin.contro.project.web.controller;

import it.academy.vladsin.contro.project.web.WebUtils;
import it.academy.vladsin.control.project.data.AuthorizationUser;
import it.academy.vladsin.control.project.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Controller
@RequestMapping
public class LoginController {

    private SecurityService securityService;
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String doGet(HttpServletRequest request, HttpServletResponse response){
        Object authorizationUser = request.getSession().getAttribute("authorizationUser");
        if (authorizationUser == null){
            return "login";
            //WebUtils.forward("login", request, response);
        }
        return "redirect:/user";
    }

    @PostMapping("/login")
    public String doPost(HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        AuthorizationUser authorizationUser = securityService.login(login, password);
        if (authorizationUser == null) {
            request.setAttribute("error", "login or password invalid");
            log.info("error authorization at {}", LocalDateTime.now());
            //WebUtils.forward("login", request, response);
            return "login";
        }

        log.info("authorizationUser {} logged at {}", authorizationUser.getLogin(), LocalDateTime.now());
        request.getSession().setAttribute("authorizationUser", authorizationUser);

        if (authorizationUser.getId().equals(1L)){
            return "redirect:/user";
        } else {
            return "redirect:/teacher";
        }
    }
}
