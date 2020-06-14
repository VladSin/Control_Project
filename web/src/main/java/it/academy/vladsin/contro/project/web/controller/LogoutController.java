package it.academy.vladsin.contro.project.web.controller;

import it.academy.vladsin.contro.project.web.WebUtils;
import it.academy.vladsin.control.project.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Controller
@RequestMapping
public class LogoutController {

    private SecurityService securityService;
    private static final Logger log = LoggerFactory.getLogger(LogoutController.class);

    public LogoutController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/logout")
    public String doGet(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("authorizationUser");
        request.getSession().invalidate();
        log.info("invalidate data at {}", LocalDateTime.now());
        //WebUtils.forward("login", request, response);
        return "login";
    }
}
