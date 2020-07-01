package it.academy.vladsin.contro.project.web.controller;

import it.academy.vladsin.control.project.data.Applicant;
import it.academy.vladsin.control.project.data.Exam;
import it.academy.vladsin.control.project.data.Student;
import it.academy.vladsin.control.project.data.University;
import it.academy.vladsin.control.project.service.ApplicantService;
import it.academy.vladsin.control.project.service.ExamService;
import it.academy.vladsin.control.project.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class ExamController {

    private ApplicantService applicantService;
    private StudentService studentService;
    private ExamService examService;
    private static final Logger log = LoggerFactory.getLogger(ExamController.class);

    public ExamController(ApplicantService applicantService, StudentService studentService, ExamService examService) {
        this.applicantService = applicantService;
        this.studentService = studentService;
        this.examService = examService;
    }

    @GetMapping("/prog")
    public String doGetProg(){
        return "/WEB-INF/view/page/prog";
    }

    @GetMapping("/math")
    public String doGetMath(){
        return "/WEB-INF/view/page/math";
    }

    @GetMapping("/phys")
    public String doGetPhys(){
        return "/WEB-INF/view/page/phys";
    }

    @PostMapping("/prog")
    @Secured("ROLE_USER")
    public String doPostProg(HttpServletRequest req) {
        int mark = 0;

        List<String> answers = new ArrayList<>();
        answers.add(req.getParameter("test[0]"));
        answers.add(req.getParameter("test[1]"));
        answers.add(req.getParameter("test[2]"));

        List<Exam> exams = new ArrayList<>();
        exams.addAll(examService.getExams(1L));

        for (int i = 0; i < answers.size(); i++) {
            if(answers.get(i).equals(exams.get(i).getAnswer())){
                mark += 10 /answers.size();
            }
        }

        Applicant applicant = new Applicant(null, (Long) req.getSession().getAttribute("userId"), 1L, mark);
        Applicant saveApplicant = applicantService.saveApplicant(applicant);
        log.info("applicant created:{} at {}", saveApplicant.getId(), LocalDateTime.now());
        req.getSession().setAttribute("applicantId", saveApplicant.getId());

        if(mark >= 9){
            University university = new University(1L, "Programming");
            Student student = new Student(null, applicant.getUserId());
            student.setUniversities(university);
            university.addStudents(student);
            studentService.saveStudent(student);
        }
        return "redirect:/result";
    }

    @PostMapping("/math")
    @Secured("ROLE_USER")
    public String doPostMath(HttpServletRequest req){
        int mark = 0;

        List<String> answers = new ArrayList<>();
        answers.add(req.getParameter("test[3]"));
        answers.add(req.getParameter("test[4]"));
        answers.add(req.getParameter("test[5]"));

        List<Exam> exams = new ArrayList<>();
        exams.addAll(examService.getExams(2L));

        for (int i = 0; i < answers.size(); i++) {
            if(answers.get(i).equals(exams.get(i).getAnswer())){
                mark += 10 /answers.size();
            }
        }

        Applicant applicant = new Applicant(null, (Long) req.getSession().getAttribute("userId"), 2L, mark);
        Applicant saveApplicant = applicantService.saveApplicant(applicant);
        log.info("applicant created:{} at {}", saveApplicant.getId(), LocalDateTime.now());
        req.getSession().setAttribute("applicantId", saveApplicant.getId());

        if(mark >= 8){
            University university = new University(2L, "Mathematics");
            Student student = new Student(null, applicant.getUserId());
            student.setUniversities(university);
            university.addStudents(student);
            studentService.saveStudent(student);
        }
        return "redirect:/result";
    }

    @PostMapping("/phys")
    @Secured("ROLE_USER")
    public String doPostPhys(HttpServletRequest req){
        int mark = 0;

        List<String> answers = new ArrayList<>();
        answers.add(req.getParameter("test[6]"));
        answers.add(req.getParameter("test[7]"));
        answers.add(req.getParameter("test[8]"));

        List<Exam> exams = new ArrayList<>();
        exams.addAll(examService.getExams(3L));

        for (int i = 0; i < answers.size(); i++) {
            if(answers.get(i).equals(exams.get(i).getAnswer())){
                mark += 10 /answers.size();
            }
        }

        Applicant applicant = new Applicant(null, (Long) req.getSession().getAttribute("userId"), 3L, mark);
        Applicant saveApplicant = applicantService.saveApplicant(applicant);
        log.info("applicant created:{} at {}", saveApplicant.getId(), LocalDateTime.now());
        req.getSession().setAttribute("applicantId", saveApplicant.getId());

        if(mark >= 8){
            University university = new University(3L, "Physics");
            Student student = new Student(null, applicant.getUserId());
            student.setUniversities(university);
            university.addStudents(student);
            studentService.saveStudent(student);
        }
       return "redirect:/result";
    }
}
