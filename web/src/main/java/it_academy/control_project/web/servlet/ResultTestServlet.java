package it_academy.control_project.web.servlet;

import it_academy.control_project.data.Applicant;
import it_academy.control_project.data.Faculty;
import it_academy.control_project.service.IApplicantService;
import it_academy.control_project.service.IFacultyService;
import it_academy.control_project.service.impl.DefaultApplicantService;
import it_academy.control_project.service.impl.DefaultFacultyService;
import it_academy.control_project.web.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/result")
public class ResultTestServlet extends HttpServlet {

    private IApplicantService applicantService = DefaultApplicantService.getInstance();
    private IFacultyService facultyService = DefaultFacultyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object applicantId = request.getSession().getAttribute("applicantId");

        Applicant applicant = applicantService.getApplicant((Long) applicantId);
        request.setAttribute("applicant", applicant);

        Faculty faculty = facultyService.getFaculty(applicant.getFacultyId());
        request.setAttribute("faculty", faculty);

        WebUtils.forward("result", request, response);
    }
}
