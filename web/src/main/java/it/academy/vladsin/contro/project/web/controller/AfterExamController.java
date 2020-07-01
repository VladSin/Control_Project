package it.academy.vladsin.contro.project.web.controller;

import it.academy.vladsin.control.project.data.Applicant;
import it.academy.vladsin.control.project.data.Exam;
import it.academy.vladsin.control.project.data.Faculty;
import it.academy.vladsin.control.project.service.ApplicantService;
import it.academy.vladsin.control.project.service.ExamService;
import it.academy.vladsin.control.project.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class AfterExamController {

    private ExamService examService;
    private ApplicantService applicantService;
    private FacultyService facultyService;
    private static final Logger log = LoggerFactory.getLogger(AfterExamController.class);

    public AfterExamController(ExamService examService, ApplicantService applicantService, FacultyService facultyService) {
        this.examService = examService;
        this.applicantService = applicantService;
        this.facultyService = facultyService;
    }

    @GetMapping("/result")
    @Secured("ROLE_USER")
    public String doGet(HttpServletRequest request){
        Object applicantId = request.getSession().getAttribute("applicantId");

        Applicant applicant = applicantService.getApplicant((Long) applicantId);
        request.setAttribute("applicant", applicant);

        Faculty faculty = facultyService.getFaculty(applicant.getFacultyId());
        request.setAttribute("faculty", faculty);

        List<Exam> exams = facultyService.getExamForFaculty();
        request.setAttribute("exams", exams);

        return "result";
    }
}
