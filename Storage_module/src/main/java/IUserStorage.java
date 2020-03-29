import java.util.List;

public interface IUserStorage {
    List<User> getUsers();
    String save(User user);
}
