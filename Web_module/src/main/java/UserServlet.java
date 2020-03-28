import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private IUserService userService = DefaultUserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getUsers();
        request.setAttribute("user", users);
        WebUtils.forword("user", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        User user = new User(name, surname, phone, email);
        String id = userService.saveUser(user);

        try {
            response.sendRedirect(request.getContextPath() + "/user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
