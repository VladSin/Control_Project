package it_academy.control_project.web.servlet;

import it_academy.control_project.data.Applicant;
import it_academy.control_project.data.Exam;
import it_academy.control_project.data.Faculty;
import it_academy.control_project.service.ApplicantService;
import it_academy.control_project.service.ExamService;
import it_academy.control_project.service.FacultyService;
import it_academy.control_project.service.impl.DefaultApplicantService;
import it_academy.control_project.service.impl.DefaultExamService;
import it_academy.control_project.service.impl.DefaultFacultyService;
import it_academy.control_project.web.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/result")
public class ResultTestServlet extends HttpServlet {

    private ExamService examService = DefaultExamService.getInstance();
    private ApplicantService applicantService = DefaultApplicantService.getInstance();
    private FacultyService facultyService = DefaultFacultyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object applicantId = request.getSession().getAttribute("applicantId");

        Applicant applicant = applicantService.getApplicant((Long) applicantId);
        request.setAttribute("applicant", applicant);

        Faculty faculty = facultyService.getFaculty(applicant.getFacultyId());
        request.setAttribute("faculty", faculty);

        List<Exam> exams = examService.getExam();
        request.setAttribute("exams", exams);

        WebUtils.forward("result", request, response);
    }
}
