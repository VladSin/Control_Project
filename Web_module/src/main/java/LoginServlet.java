import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private ISecurityService securityService = DefaultSecurityService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object authorizationUser = request.getSession().getAttribute("authorizationUser");
        if (authorizationUser == null){
            WebUtils.forword("login", request, response);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        AuthorizationUser user = securityService.login(login, password);
        if(user == null){
            request.setAttribute("error", "Login or password invalid");
            WebUtils.forword("login", request, response);
        }
        request.getSession().setAttribute("authorizationUser", user);
        try {
            response.sendRedirect(request.getContextPath() + "/user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
