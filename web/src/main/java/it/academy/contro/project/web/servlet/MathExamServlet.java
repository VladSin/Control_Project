package it.academy.contro.project.web.servlet;

import it.academy.control.project.data.Applicant;
import it.academy.control.project.service.ApplicantService;
import it.academy.control.project.service.impl.DefaultApplicantService;
import it.academy.contro.project.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@WebServlet("/math")
public class MathExamServlet extends HttpServlet {

    private ApplicantService applicantService = DefaultApplicantService.getInstance();
    private static final Logger log = LoggerFactory.getLogger(MathExamServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebUtils.forward("math", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mark = 0;
        String answer1 = req.getParameter("test[3]");
        String answer2 = req.getParameter("test[4]");
        String answer3 = req.getParameter("test[5]");

        if (answer1.equals("2")){ mark+=3; }
        if (answer2.equals("2")){ mark+=3; }
        if (answer3.equals("1")){ mark+=3; }

        Applicant applicant = new Applicant(null, (Long) req.getSession().getAttribute("userId"), 2L, mark);

        Applicant saveApplicant = applicantService.saveApplicant(applicant);
        log.info("applicant created:{} at {}", saveApplicant.getId(), LocalDateTime.now());
        req.getSession().setAttribute("applicantId", saveApplicant.getId());
        WebUtils.redirect("/result", req, resp);
    }
}
