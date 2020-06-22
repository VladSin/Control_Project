package it.academy.vladsin.contro.project.web.controller;

import it.academy.vladsin.control.project.data.Student;
import it.academy.vladsin.control.project.data.User;
import it.academy.vladsin.control.project.service.StudentService;
import it.academy.vladsin.control.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class StudentsController {

    private StudentService studentService ;
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    public StudentsController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping("/student")
    public String doGet(HttpServletRequest request){
        List<Student> students = studentService.getStudents();
        request.setAttribute("students", students);

        List<User> users = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            users.add(userService.getUser(students.get(i).getUserId()));
        }
        request.setAttribute("users", users);
        return "student";
    }
}
