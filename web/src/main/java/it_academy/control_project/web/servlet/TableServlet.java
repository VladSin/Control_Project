package it_academy.control_project.web.servlet;

import it_academy.control_project.data.Applicant;
import it_academy.control_project.data.Faculty;
import it_academy.control_project.data.User;
import it_academy.control_project.service.IApplicantService;
import it_academy.control_project.service.IFacultyService;
import it_academy.control_project.service.IUserService;
import it_academy.control_project.service.impl.DefaultApplicantService;
import it_academy.control_project.service.impl.DefaultFacultyService;
import it_academy.control_project.service.impl.DefaultUserService;
import it_academy.control_project.web.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/table")
public class TableServlet extends HttpServlet {

    private IApplicantService applicantService = DefaultApplicantService.getInstance();
    private IUserService userService = DefaultUserService.getInstance();
    private IFacultyService facultyService = DefaultFacultyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Applicant> applicants = applicantService.getApplicant();
        request.setAttribute("applicants", applicants);

        List<User> users = userService.getUser();
        request.setAttribute("users", users);

        List<Faculty> faculties = facultyService.getFaculty();
        request.setAttribute("faculties", faculties);

        WebUtils.forward("table", request, response);
    }
}
