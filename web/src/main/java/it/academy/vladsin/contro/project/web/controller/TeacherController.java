package it.academy.vladsin.contro.project.web.controller;

import it.academy.vladsin.control.project.data.Applicant;
import it.academy.vladsin.control.project.data.Faculty;
import it.academy.vladsin.control.project.data.User;
import it.academy.vladsin.control.project.service.ApplicantService;
import it.academy.vladsin.control.project.service.FacultyService;
import it.academy.vladsin.control.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TeacherController {

    private ApplicantService applicantService;
    private UserService userService;
    private FacultyService facultyService;
    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    public TeacherController(ApplicantService applicantService, UserService userService, FacultyService facultyService) {
        this.applicantService = applicantService;
        this.userService = userService;
        this.facultyService = facultyService;
    }

    @GetMapping("/teacher")
    public String doGetTeacher(){
        return "table";
    }

    @GetMapping("/table")
    public String doGetTable(HttpServletRequest request){

        List<Applicant> applicants = applicantService.getApplicants();
        request.setAttribute("applicants", applicants);

        List<User> users = userService.getUsers();
        request.setAttribute("users", users);

        List<Faculty> faculties = facultyService.getFaculties();
        request.setAttribute("faculties", faculties);

        return "table";
    }
}
