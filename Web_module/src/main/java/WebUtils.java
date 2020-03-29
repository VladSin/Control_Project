import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {
    private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);

    public static void forword(String page, HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher("/" + page + ".jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
