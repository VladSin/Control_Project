package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.IAuthUserStorage;
import it_academy.control_project.data.AuthorizationUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultAuthUserStorage implements IAuthUserStorage {

    private static final Logger log = LoggerFactory.getLogger(DefaultAuthUserStorage.class);

    private static class SingletonHolder {
        static final IAuthUserStorage HOLDER_INSTANCE = new DefaultAuthUserStorage();
    }
    public static IAuthUserStorage getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private Connection getConnection(){
        return DataSource.getInstance().getConnection();
    }


    @Override
    public AuthorizationUser saveAuthUser(AuthorizationUser authorizationUser) {
        try(PreparedStatement statement = getConnection().prepareStatement("insert into auth_user(login, password, role) values(?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, authorizationUser.getLogin());
            statement.setString(2, authorizationUser.getPassword());
            statement.setString(3, authorizationUser.getRole());
            statement.executeUpdate();
            final long id;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                generatedKeys.next();
                id = generatedKeys.getLong(1);
            }
            final AuthorizationUser authUserLog = new AuthorizationUser(id, authorizationUser.getLogin(), authorizationUser.getPassword(), authorizationUser.getRole());
            log.info("authUser saved:{}", authUserLog);
            return authUserLog;
        } catch (SQLException e) {
            log.error("fail to save authUser:{}", authorizationUser, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AuthorizationUser> saveAuthUser(List<AuthorizationUser> authorizationUsers) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("insert into auth_user(login, password, role) values(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                for (AuthorizationUser authorizationUser : authorizationUsers) {
                    statement.setString(1, authorizationUser.getLogin());
                    statement.setString(2, authorizationUser.getPassword());
                    statement.setString(3, authorizationUser.getRole());
                    statement.addBatch();
                }
                statement.executeBatch();
                final ResultSet generatedKeys = statement.getGeneratedKeys();
                final ArrayList<AuthorizationUser> result = new ArrayList<>();
                for (AuthorizationUser authorizationUser : authorizationUsers) {
                    generatedKeys.next();
                    final long id = generatedKeys.getLong(1);
                    result.add(new AuthorizationUser(id, authorizationUser.getLogin(), authorizationUser.getPassword(), authorizationUser.getRole()));
                }
                log.info("authUser saved: {}", result);
                connection.commit();
                return result;
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(e);
            }
            log.error("fail to save authUser:{}", authorizationUsers, e);
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
    public boolean deleteAuthUser(long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("delete from auth_user where id = ?")) {
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
    public boolean updateAuthUser(AuthorizationUser authorizationUser) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("update auth_user set login = ?, password = ?, role = ? where id = ?")) {
                statement.setString(1, authorizationUser.getLogin());
                statement.setString(2, authorizationUser.getPassword());
                statement.setString(3, authorizationUser.getRole());
                statement.setLong(4, authorizationUser.getId());
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
    public AuthorizationUser getAuthUser(long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("select * from auth_user where id = ?")) {
                statement.setLong(1, id);
                final ResultSet resultSet = statement.executeQuery();
                connection.commit();
                final boolean exist = resultSet.next();
                if (!exist) {
                    return null;
                }
                final long resultId = resultSet.getLong("id");
                final String login = resultSet.getString("login");
                final String password = resultSet.getString("password");
                final String role = resultSet.getString("role");
                return new AuthorizationUser(resultId, login, password, role);
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
    public List<AuthorizationUser> getAuthUser() {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from auth_user");
            ResultSet resultSet = preparedStatement.executeQuery()) {

            final ArrayList<AuthorizationUser> result = new ArrayList<>();
            while (resultSet.next()){
                final AuthorizationUser authorizationUser = new AuthorizationUser(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                result.add(authorizationUser);
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthorizationUser getLogin(String login) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("select * from auth_user where login = ?")) {
                statement.setString(1, login);
                final ResultSet resultSet = statement.executeQuery();
                connection.commit();
                final boolean exist = resultSet.next();
                if (!exist) {
                    return null;
                }
                final long id = resultSet.getLong("id");
                final String resultLogin = resultSet.getString("login");
                final String password = resultSet.getString("password");
                final String role = resultSet.getString("role");
                return new AuthorizationUser(id, resultLogin, password, role);
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
}