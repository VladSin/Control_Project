package it.academy.vladsin.contro.project.web.controller;

import it.academy.vladsin.control.project.data.User;
import it.academy.vladsin.control.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping
public class UserController {

    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String doGet(){
        return "user";
    }

    @PostMapping("/user")
    public String doPost(HttpServletRequest request){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        if (name.equals("") || surname.equals("") || phone.equals("") || email.equals("")){
            request.setAttribute("error", "incorrect data");
            return "user";
        }

        User user = new User(null, name, surname, phone, email);
        User saveUser = userService.saveUser(user);
        log.info("user created:{} at {}", saveUser.getId(), LocalDateTime.now());
        request.getSession().setAttribute("userId", saveUser.getId());

        return "redirect:/faculty";
    }
}
