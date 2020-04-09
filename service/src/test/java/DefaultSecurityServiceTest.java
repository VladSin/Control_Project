import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultSecurityServiceTest {

    @Test
    public void login() {
        String login = "some login";
        String password = "some password";
        IAuthUserStorage authUserStorage = new DefaultAuthUserStorage();
        AuthorizationUser user = new  AuthorizationUser(login, password);
        assertTrue(user.getPassword().equals(password));
    }
}
