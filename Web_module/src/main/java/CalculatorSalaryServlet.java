import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/salary")
public class CalculatorSalaryServlet extends HttpServlet {
    private ISalaryService salaryService = DefaultSalaryService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Salary> salaries = salaryService.getSalary();
        request.setAttribute("salaries", salaries);
        WebUtils.forword("salary", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameter = request.getParameter("salary");
        Salary salary = calculateValue(parameter);
        String id = salaryService.saveSalary(salary);

        try {
            response.sendRedirect(request.getContextPath() + "/view.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
