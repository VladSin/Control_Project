package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.IUserStorage;
import it_academy.control_project.data.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserStorage implements IUserStorage {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserStorage.class);

    private static class SingletonHolder{
        static final IUserStorage HOLDER_INSTANCE = new DefaultUserStorage();
    }
    public static IUserStorage getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private Connection getConnection(){
        return DataSource.getInstance().getConnection();
    }

    @Override
    public User saveUser(User user) {
        try(PreparedStatement statement = getConnection().prepareStatement("insert into user(name, surname, phone, email) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
            final long id;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                generatedKeys.next();
                id = generatedKeys.getLong(1);
            }
            final User userLog = new User(id, user.getName(), user.getSurname(), user.getPhone(), user.getEmail());
            log.info("user saved:{}", userLog);
            return userLog;
        } catch (SQLException e) {
            log.error("fail to save user:{}", user, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> saveUser(List<User> users) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("insert into user(name, surname, phone, email) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                for (User user : users) {
                    statement.setString(1, user.getName());
                    statement.setString(2, user.getSurname());
                    statement.setString(3, user.getPhone());
                    statement.setString(4, user.getEmail());
                    statement.addBatch();
                }
                statement.executeBatch();
                final ResultSet generatedKeys = statement.getGeneratedKeys();
                final ArrayList<User> result = new ArrayList<>();
                for (User user : users) {
                    generatedKeys.next();
                    final long id = generatedKeys.getLong(1);
                    result.add(new User(id, user.getName(), user.getSurname(), user.getPhone(), user.getEmail()));
                }
                log.info("user saved: {}", result);
                connection.commit();
                return result;
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(e);
            }
            log.error("fail to save user:{}", users, e);
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("fail close connection", e);
                }
            }
        }
    }

    @Override
    public boolean deleteUser(long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("delete from user where id = ?")) {
                statement.setLong(1, id);
                final int count = statement.executeUpdate();
                connection.commit();
                return count > 0;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("fail close connection", e);
                }
            }
        }
    }

    @Override
    public boolean updateUser(User user) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("update user set name = ?, surname = ?, phone = ?, email = ? where id = ?")) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getSurname());
                statement.setString(3, user.getPhone());
                statement.setString(4, user.getEmail());
                statement.setLong(5, user.getId());
                final int count = statement.executeUpdate();
                connection.commit();
                return count > 0;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("fail close connection", e);
                }
            }
        }
    }

    @Override
    public User getUser(long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("select * from user where id = ?")) {
                statement.setLong(1, id);
                final ResultSet resultSet = statement.executeQuery();
                connection.commit();
                final boolean exist = resultSet.next();
                if (!exist) {
                    return null;
                }
                final long resultId = resultSet.getLong("id");
                final String name = resultSet.getString("name");
                final String surname = resultSet.getString("surname");
                final String phone = resultSet.getString("phone");
                final String email = resultSet.getString("email");
                return new User(resultId, name, surname, phone, email);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("fail close connection", e);
                }
            }
        }
    }

    @Override
    public List<User> getUser() {
        try(Connection connection = getConnection();
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
}
