import java.util.List;

public class DefaultUserService implements IUserService{

    private static volatile IUserService instance;
    public static IUserService getInstance(){
        IUserService localInstance = instance;
        if (localInstance == null){
            localInstance = instance;
            if(localInstance == null){
                instance = localInstance = new DefaultUserService();
            }
        }
        return localInstance;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public String saveUser(User user) {
        return null;
    }

    @Override
    public void saveAuthorizationUser(AuthorizationUser authorizationUser) {

    }
}
