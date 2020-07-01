package it.academy.vladsin.contro.project.web.controller;

import it.academy.vladsin.control.project.data.AuthorizationUser;
import it.academy.vladsin.control.project.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class LoginController {

    private SecurityService securityService;
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String doGet(HttpServletRequest request){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || "anonymousUser".equals(authentication.getPrincipal())){
            return "login";
        }
        return "user";
    }

    @PostMapping("/login")
    public String doPost(HttpServletRequest request){
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        AuthorizationUser authorizationUser = securityService.login(login, password);
        if (authorizationUser == null) {
            request.setAttribute("error", "login or password invalid");
            log.info("error authorization at {}", LocalDateTime.now());
            return "login";
        }
        log.info("authorizationUser {} logged at {}", authorizationUser.getLogin(), LocalDateTime.now());

        Authentication authentication = new UsernamePasswordAuthenticationToken(authorizationUser, null, getAuthorities(authorizationUser.getId()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authorizationUser.getId().equals(1L)){
            return "redirect:/user";
        } else {
            return "redirect:/teacher";
        }
    }

    private List<GrantedAuthority> getAuthorities(long id) {
            if(id == 1L){
            return Arrays.asList(
                    (GrantedAuthority) () -> "ROLE_USER");
        } else {
            return Arrays.asList(
                    (GrantedAuthority) () -> "ROLE_TEACHER");
        }

    }
}
