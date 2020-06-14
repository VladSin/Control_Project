package it.academy.vladsin.contro.project.web.controller;

import it.academy.vladsin.contro.project.web.WebUtils;
import it.academy.vladsin.control.project.data.Faculty;
import it.academy.vladsin.control.project.service.FacultyService;
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
public class FacultyController {

    private FacultyService facultyService;
    private static final Logger log = LoggerFactory.getLogger(FacultyController.class);

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/faculty")
    public String doGet(HttpServletRequest request, HttpServletResponse response){
        return "exam";
        //WebUtils.forward("exam", request, response);
    }

    @PostMapping("/faculty")
    public String doPost(HttpServletRequest request, HttpServletResponse response){
        String subject = request.getParameter("exam");
        if (subject == null){
            //WebUtils.forward("exam", request, response);
            return "exam";
        } else if (subject.equals("Programming")){

            Faculty faculty = facultyService.getFaculty(1);
            log.info("faculty {} logged at {}", 1, LocalDateTime.now());
            request.getSession().setAttribute("test", faculty);
            return "redirect:/prog";
        } else if (subject.equals("Mathematics")){

            Faculty faculty = facultyService.getFaculty(2);
            log.info("faculty {} logged at {}", 2, LocalDateTime.now());
            request.getSession().setAttribute("test", faculty);
            return "redirect:/math";
        } else if (subject.equals("Physics")){

            Faculty faculty = facultyService.getFaculty(3);
            log.info("faculty {} logged at {}", 3, LocalDateTime.now());
            request.getSession().setAttribute("test", faculty);
            return "redirect:/phys";
        }
        //WebUtils.forward("exam", request, response);
        return "exam";
    }
}
