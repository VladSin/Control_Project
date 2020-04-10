import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserStorage implements IUserStorage{


    private static class SingletonHolder{
        static final IUserStorage HOLDER_INSTANCE = new DefaultUserStorage();
    }
    public static IUserStorage getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public List<User> getUsers() {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
            ResultSet resultSet = preparedStatement.executeQuery()) {

            final ArrayList<User> result = new ArrayList<>();
            while (resultSet.next()){
                final User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"));
                result.add(user);
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long save(User user) {
        final String sql = "insert into user(name, surname, phone, email) values(?,?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();
            try (ResultSet keys = preparedStatement.getGeneratedKeys()){
                keys.next();
                return keys.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
