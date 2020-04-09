import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private ISecurityService securityService = DefaultSecurityService.getInstance();
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object authorizationUser = request.getSession().getAttribute("authorizationUser");
        if (authorizationUser == null){
            WebUtils.forword("login", request, response);
            return;
        }
        WebUtils.redirect("/user", request, response);
        /*try {
            response.sendRedirect(request.getContextPath() + "/user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
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
        log.info("user {} logged", user.getLogin());
        request.getSession().setAttribute("authorizationUser", user);
        WebUtils.redirect("/user", request, response);
        /*try {
            response.sendRedirect(request.getContextPath() + "/user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
}
