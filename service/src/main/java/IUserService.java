import java.util.List;

public interface IUserService {
    List<User> getUsers();
    Long saveUser(User user);
    void saveAuthorizationUser(AuthorizationUser authorizationUser);
}
