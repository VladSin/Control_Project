package it.academy.vladsin.contro.project.web.servlet;

import it.academy.vladsin.control.project.data.Applicant;
import it.academy.vladsin.control.project.data.Student;
import it.academy.vladsin.control.project.data.University;
import it.academy.vladsin.control.project.service.ApplicantService;
import it.academy.vladsin.control.project.service.StudentService;
import it.academy.vladsin.control.project.service.impl.DefaultApplicantService;
import it.academy.vladsin.contro.project.web.WebUtils;
import it.academy.vladsin.control.project.service.impl.DefaultStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/phys")
public class PhysExamServlet extends HttpServlet {

    private ApplicantService applicantService = DefaultApplicantService.getInstance();
    private StudentService studentService = DefaultStudentService.getInstance();

    private static final Logger log = LoggerFactory.getLogger(PhysExamServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebUtils.forward("phys", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mark = 0;
        String answer1 = req.getParameter("test[6]");
        String answer2 = req.getParameter("test[7]");
        String answer3 = req.getParameter("test[8]");

        if (answer1.equals("0")){ mark+=3; }
        if (answer2.equals("0")){ mark+=3; }
        if (answer3.equals("4")){ mark+=3; }

        Applicant applicant = new Applicant(null, (Long) req.getSession().getAttribute("userId"), 3L, mark);
        Applicant saveApplicant = applicantService.saveApplicant(applicant);
        log.info("applicant created:{} at {}", saveApplicant.getId(), LocalDateTime.now());
        req.getSession().setAttribute("applicantId", saveApplicant.getId());

        if(mark >= 8){
            University university = new University(2L, "Physics");
            Student student = new Student(null, applicant.getUserId());
            student.setUniversities(university);
            university.addStudents(student);
            studentService.saveStudent(student);
        }

        WebUtils.redirect("/result", req, resp);
    }
}
