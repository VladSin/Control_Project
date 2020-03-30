
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultSecurityServiceTest {

    @Test
    public void login() {
        String login = "some login";
        String password = "some password";
        AuthorizationUser user = new  AuthorizationUser(login, password);
        assertTrue(user.getPassword().equals(password));
    }
}
