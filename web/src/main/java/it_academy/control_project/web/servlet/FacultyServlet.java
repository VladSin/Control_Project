package it_academy.control_project.web.servlet;

import it_academy.control_project.data.Faculty;
import it_academy.control_project.data.User;
import it_academy.control_project.service.impl.DefaultFacultyService;
import it_academy.control_project.service.IFacultyService;
import it_academy.control_project.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {

    private IFacultyService facultyService = DefaultFacultyService.getInstance();
    private static final Logger log = LoggerFactory.getLogger(FacultyServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebUtils.forward("exam", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subject = request.getParameter("exam");
        if (subject == null){
            WebUtils.forward("exam", request, response);
        } else if (subject.equals("Programming")){

            Faculty exam = facultyService.getFaculty(1);
            log.info("faculty {} logged", exam.getId());
            request.getSession().setAttribute("test", exam);
            WebUtils.redirect("/prog", request, response);
            return;
        } else if (subject.equals("Mathematics")){

            Faculty exam = facultyService.getFaculty(2);
            log.info("faculty {} logged", exam.getId());
            request.getSession().setAttribute("test", exam);
            WebUtils.redirect("/math", request, response);
            return;
        } else if (subject.equals("Physics")){

            Faculty exam = facultyService.getFaculty(3);
            log.info("faculty {} logged", exam.getId());
            request.getSession().setAttribute("test", exam);
            WebUtils.redirect("/phys", request, response);
            return;
        }
        WebUtils.forward("exam", request, response);
    }
}
