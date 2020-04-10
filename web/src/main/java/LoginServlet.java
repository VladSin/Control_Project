import it_academy.control_project.data.university.Faculty;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private ISecurityService securityService = DefaultSecurityService.getInstance();
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);


    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/university";
    static final String DATABASE_USER = "root";
    static final String DATABASE_PASSWORD = "root";
    static final String GET_ALL = "SELECT * FROM auth_user";

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

        try{
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()){
                String dbLogin = resultSet.getString(1);
                String dbPassword = resultSet.getString(2);
                String dbRole = resultSet.getString(3);
                if(login.equals(dbLogin) && password.equals(dbPassword)){
                    if(dbRole.equals("APPLICANT")){
                        WebUtils.redirect("/faculty", request, response);
                    } else {
                        WebUtils.redirect("/faculty", request, response);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
        WebUtils.forword("login", request, response);
        /*AuthorizationUser user = securityService.login(login, password);
        if(user == null){
            request.setAttribute("error", "Login or password invalid");
            WebUtils.forword("login", request, response);
        }
        assert user != null;
        log.info("user {} logged", user.getLogin());
        request.getSession().setAttribute("authorizationUser", user);
        WebUtils.redirect("/user", request, response);*/
    }
}
