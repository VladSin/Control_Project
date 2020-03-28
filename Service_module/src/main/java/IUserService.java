import java.util.List;

public interface IUserService {
    List<User> getUsers();
    String saveUser(User user);
    void saveAuthorizationUser(AuthorizationUser authorizationUser);
}
