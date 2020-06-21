package it.academy.vladsin.contro.project.web.controller;

import it.academy.vladsin.control.project.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class LogoutController {

    private SecurityService securityService;
    private static final Logger log = LoggerFactory.getLogger(LogoutController.class);

    public LogoutController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/logout")
    public String doGet(HttpServletRequest request){
        request.getSession().removeAttribute("authorizationUser");
        request.getSession().invalidate();
        log.info("invalidate data at {}", LocalDateTime.now());
        return "login";
    }
}
