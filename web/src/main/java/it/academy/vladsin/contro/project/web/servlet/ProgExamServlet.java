package it.academy.vladsin.contro.project.web.servlet;

import it.academy.vladsin.control.project.data.Applicant;
import it.academy.vladsin.control.project.data.Student;
import it.academy.vladsin.control.project.data.University;
import it.academy.vladsin.control.project.service.ApplicantService;
import it.academy.vladsin.control.project.service.StudentService;
import it.academy.vladsin.control.project.service.UserService;
import it.academy.vladsin.control.project.service.impl.DefaultApplicantService;
import it.academy.vladsin.contro.project.web.WebUtils;
import it.academy.vladsin.control.project.service.impl.DefaultStudentService;
import it.academy.vladsin.control.project.service.impl.DefaultUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/prog")
public class ProgExamServlet extends HttpServlet {

    private ApplicantService applicantService = DefaultApplicantService.getInstance();
    private StudentService studentService = DefaultStudentService.getInstance();
    private UserService userService = DefaultUserService.getInstance();

    private static final Logger log = LoggerFactory.getLogger(ProgExamServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebUtils.forward("prog", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mark = 0;
        String answer1 = req.getParameter("test[0]");
        String answer2 = req.getParameter("test[1]");
        String answer3 = req.getParameter("test[2]");

        if (answer1.equals("3")){ mark+=3; }
        if (answer2.equals("0")){ mark+=3; }
        if (answer3.equals("3")){ mark+=3; }

        Applicant applicant = new Applicant(null, (Long) req.getSession().getAttribute("userId"), 1L, mark);
        Applicant saveApplicant = applicantService.saveApplicant(applicant);
        log.info("applicant created:{} at {}", saveApplicant.getId(), LocalDateTime.now());
        req.getSession().setAttribute("applicantId", saveApplicant.getId());

        if(mark >= 9){
            University university = new University(2L, "Programming");
            Student student = new Student(null, applicant.getUserId());
            student.setUniversities(university);
            university.addStudents(student);
            studentService.saveStudent(student);
        }

        WebUtils.redirect("/result", req, resp);
    }
}
