package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.IFacultyStorage;
import it_academy.control_project.data.Faculty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultFacultyStorage implements IFacultyStorage {

    private static class SingletonHolder{
        static final IFacultyStorage HOLDER_INSTANCE = new DefaultFacultyStorage();
    }
    public static IFacultyStorage getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public List<Faculty> getFaculty() {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from faculty");
            ResultSet resultSet = preparedStatement.executeQuery()) {

            final ArrayList<Faculty> result = new ArrayList<>();
            while (resultSet.next()){
                final Faculty faculty = new Faculty(
                        resultSet.getLong("id"),
                        resultSet.getString("faculty"),
                        resultSet.getInt("Mark"));
                result.add(faculty);
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long save(Faculty faculty) {
        final String sql = "insert into faculty(faculty, Mark) values(?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, faculty.getFaculty());
            preparedStatement.setInt(2, faculty.getMark());
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
