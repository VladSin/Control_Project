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

    @Override
    public AuthorizationUser login(String login, String password) {
        //TODO...
        return null;
    }
}
