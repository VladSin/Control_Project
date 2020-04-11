package it_academy.control_project.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {

    private final ComboPooledDataSource pool;

    public DataSource(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        pool = new ComboPooledDataSource();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        String url = resourceBundle.getString("url");
        String user = resourceBundle.getString("user");
        String password = resourceBundle.getString("password");

        pool.setJdbcUrl(url);
        pool.setUser(user);
        pool.setPassword(password);

        pool.setMinPoolSize(5);
        pool.setAcquireIncrement(5);
        pool.setMinPoolSize(10);
        pool.setMaxStatements(180);
    }

    private static class SingletonHolder{
        static final DataSource HOLDER_INSTANCE = new DataSource();
    }
    public static DataSource getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public Connection getConnection(){
        try {
            return this.pool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
