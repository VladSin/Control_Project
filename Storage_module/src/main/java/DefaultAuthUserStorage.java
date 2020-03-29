import java.util.HashMap;
import java.util.Map;

public class DefaultAuthUserStorage implements IAuthUserStorage {

    Map<String, AuthorizationUser> userLogin;
    private static volatile IAuthUserStorage instance;

    public DefaultAuthUserStorage(){
        this.userLogin = new HashMap<>();
        this.userLogin.putIfAbsent("user", new AuthorizationUser("user", "user"));
    }

    public static IAuthUserStorage getInstance(){
        IAuthUserStorage localInstance = instance;
        if (localInstance == null){
            synchronized (IAuthUserStorage.class){
                localInstance = instance;
                if (localInstance == null){
                    instance = localInstance = new DefaultAuthUserStorage();
                }
            }
        }
        return localInstance;
    }

    @Override
    public AuthorizationUser getLogin(String login) {
        return userLogin.get(login);
    }

    @Override
    public void saveAuthUser(AuthorizationUser user) {
        userLogin.putIfAbsent(user.getLogin(), user);
    }
}
