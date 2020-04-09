import java.util.List;

public interface IUserStorage {
    List<User> getUsers();
    Long save(User user);
}
