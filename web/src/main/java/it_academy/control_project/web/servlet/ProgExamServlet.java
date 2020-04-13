package it_academy.control_project.web.servlet;

import it_academy.control_project.data.Applicant;
import it_academy.control_project.service.IApplicantService;
import it_academy.control_project.service.IExamService;
import it_academy.control_project.service.impl.DefaultApplicantService;
import it_academy.control_project.service.impl.DefaultExamService;
import it_academy.control_project.web.WebUtils;
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

    private IExamService examService = DefaultExamService.getInstance();
    private IApplicantService applicantService = DefaultApplicantService.getInstance();
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
        WebUtils.redirect("result", req, resp);
    }
}
