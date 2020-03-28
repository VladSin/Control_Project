import java.util.List;

public class DefaultUserService implements IUserService{

    private IUserStorage userStorage = DefaultUserStorage.getInstance();
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
        return userStorage.getUsers();
    }

    @Override
    public String saveUser(User user) {
        return userStorage.save(user);
    }

    @Override
    public void saveAuthorizationUser(AuthorizationUser authorizationUser) {

    }
}
