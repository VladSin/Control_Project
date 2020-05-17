package it.academy.contro.project.web.servlet;

        import it.academy.control.project.data.Applicant;
        import it.academy.control.project.data.Faculty;
        import it.academy.control.project.data.User;
        import it.academy.control.project.service.ApplicantService;
        import it.academy.control.project.service.FacultyService;
        import it.academy.control.project.service.UserService;
        import it.academy.control.project.service.impl.DefaultApplicantService;
        import it.academy.control.project.service.impl.DefaultFacultyService;
        import it.academy.control.project.service.impl.DefaultUserService;
        import it.academy.contro.project.web.WebUtils;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.List;

@WebServlet("/table")
public class TableServlet extends HttpServlet {

    private ApplicantService applicantService = DefaultApplicantService.getInstance();
    private UserService userService = DefaultUserService.getInstance();
    private FacultyService facultyService = DefaultFacultyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Applicant> applicants = applicantService.getApplicants();
        request.setAttribute("applicants", applicants);

        List<User> users = userService.getUsers();
        request.setAttribute("users", users);

        List<Faculty> faculties = facultyService.getFaculties();
        request.setAttribute("faculties", faculties);

        WebUtils.forward("table", request, response);
    }
}
