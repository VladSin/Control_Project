import java.util.ArrayList;
import java.util.List;

public class DefaultUserStorage implements IUserStorage{

    private List<User> users;
    private static volatile IUserStorage instance;

    public DefaultUserStorage(){
        this.users = new ArrayList<>();
    }

    public static IUserStorage getInstance(){
        IUserStorage localInstance = instance;
        if(localInstance == null){
            synchronized (IUserStorage.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DefaultUserStorage();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public String save(User user) {
        users.add(user);
        return user.getSurname();
    }
}
