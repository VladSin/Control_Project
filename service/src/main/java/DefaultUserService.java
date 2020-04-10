import java.util.List;

public class DefaultUserService implements IUserService{

    private static class SingletonHolder{
        static final IUserService HOLDER_INSTANCE = new DefaultUserService();
    }

    public static IUserService getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }
    @Override
    public List<User> getUsers() {
        return DefaultUserStorage.getInstance().getUsers();
    }

    @Override
    public Long saveUser(User user) {
        return DefaultUserStorage.getInstance().save(user);
    }
}
