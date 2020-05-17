package it.academy.contro.project.web.servlet;

import it.academy.control.project.data.Applicant;
import it.academy.control.project.data.Exam;
import it.academy.control.project.data.Faculty;
import it.academy.control.project.service.ApplicantService;
import it.academy.control.project.service.ExamService;
import it.academy.control.project.service.FacultyService;
import it.academy.control.project.service.impl.DefaultApplicantService;
import it.academy.control.project.service.impl.DefaultExamService;
import it.academy.control.project.service.impl.DefaultFacultyService;
import it.academy.contro.project.web.WebUtils;

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

        List<Exam> exams = examService.getExams();
        request.setAttribute("exams", exams);

        WebUtils.forward("result", request, response);
    }
}
