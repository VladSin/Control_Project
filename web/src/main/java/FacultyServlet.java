import it_academy.control_project.data.university.Faculty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/university";
    static final String DATABASE_USER = "root";
    static final String DATABASE_PASSWORD = "root";
    static final String GET_ALL = "SELECT * FROM faculty";

    private IFacultyService facultyService = DefaultFacultyService.getInstance();
    private static final Logger log = LoggerFactory.getLogger(FacultyServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebUtils.forword("exam", request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String faculty = request.getParameter("faculty");
        Faculty facultyForExam = null;

        try{
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()){
                String dbFaculty = resultSet.getString(1);
                if(faculty.equals(dbFaculty)){
                    Long dbId = resultSet.getLong(0);
                    int dbMark = resultSet.getInt(2);
                    facultyForExam = new Faculty(dbId, dbFaculty, dbMark);
                    break;
                }
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }



        WebUtils.redirect("/faculty", request, response);
    }
}
