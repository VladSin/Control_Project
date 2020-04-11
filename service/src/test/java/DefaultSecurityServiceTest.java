import it_academy.control_project.dao.impl.DefaultAuthUserStorage;
import it_academy.control_project.dao.IAuthUserStorage;
import it_academy.control_project.data.AuthorizationUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultSecurityServiceTest {

    @Test
    public void login() {
        String login = "some login";
        String password = "some password";
        IAuthUserStorage authUserStorage = new DefaultAuthUserStorage();
        AuthorizationUser user = new AuthorizationUser(login, password);
        assertTrue(user.getPassword().equals(password));
    }
}
