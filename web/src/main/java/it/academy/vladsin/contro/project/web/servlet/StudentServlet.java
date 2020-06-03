package it.academy.vladsin.contro.project.web.servlet;

import it.academy.vladsin.contro.project.web.WebUtils;
import it.academy.vladsin.control.project.data.Student;
import it.academy.vladsin.control.project.data.User;
import it.academy.vladsin.control.project.service.StudentService;
import it.academy.vladsin.control.project.service.UserService;
import it.academy.vladsin.control.project.service.impl.DefaultStudentService;
import it.academy.vladsin.control.project.service.impl.DefaultUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentService studentService = DefaultStudentService.getInstance();
    private UserService userService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentService.getStudents();
        request.setAttribute("students", students);

        List<User> users = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            users.add(userService.getUser(students.get(i).getUserId()));
        }
        request.setAttribute("users", users);

        WebUtils.forward("student", request, response);
    }
}
