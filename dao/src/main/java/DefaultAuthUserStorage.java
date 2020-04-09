import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DefaultAuthUserStorage implements IAuthUserStorage {

   private static class SingletonHolder {
       static final IAuthUserStorage HOLDER_INSTANCE = new DefaultAuthUserStorage();
   }

   public static IAuthUserStorage getInstance(){
       return SingletonHolder.HOLDER_INSTANCE;
   }

    @Override
    public AuthorizationUser getLogin(String login) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from authorization where login = ?")){
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    return new AuthorizationUser(
                            resultSet.getLong("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            Role.valueOf(resultSet.getString("role")),
                            resultSet.getLong("user_id"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long saveAuthUser(AuthorizationUser user) {
        final String sql = "insert into authorization(login, password, role, user_id) values(?,?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().name());
            preparedStatement.setLong(4, user.getUserId());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
