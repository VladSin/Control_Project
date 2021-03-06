package it.academy.vladsin.contro.project.web.controller;

import it.academy.vladsin.control.project.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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
    public String doGet(HttpServletRequest request){
        SecurityContextHolder.clearContext();
        try{
            request.logout();
        } catch (ServletException e) {
            log.info("invalidate data at {}", LocalDateTime.now());
            throw new RuntimeException();
        }
        return "login";
    }
}
