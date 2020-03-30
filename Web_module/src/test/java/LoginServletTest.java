import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class LoginServletTest extends Mockito {

    @Test
    void doGet() throws ServletException, IOException {

        final LoginServlet servlet = new LoginServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getSession().getAttribute("authorizationUser")).getMock();
        servlet.doGet(request, response);
        verify(request, times(1));
    }
}