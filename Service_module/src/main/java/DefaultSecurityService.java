public class DefaultSecurityService implements ISecurityService {

    private static volatile ISecurityService instance;
    public static ISecurityService getInstance(){
        ISecurityService localInstance = instance;
        if(localInstance == null){
            synchronized (ISecurityService.class){
                localInstance = instance;
                if (localInstance == null){
                    instance = localInstance = new DefaultSecurityService();
                }
            }
        }
        return localInstance;
    }

    private IAuthUserStorage authUserStorage = DefaultAuthUserStorage.getInstance();
    @Override
    public AuthorizationUser login(String login, String password) {
        AuthorizationUser user = authUserStorage.getLogin(login);
        if (user == null){
            return null;
        }
        if (user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}
