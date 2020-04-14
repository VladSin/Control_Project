package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.IFacultyStorage;
import it_academy.control_project.data.Faculty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultFacultyStorage implements IFacultyStorage {

    private static final Logger log = LoggerFactory.getLogger(DefaultFacultyStorage.class);

    private static class SingletonHolder{
        static final IFacultyStorage HOLDER_INSTANCE = new DefaultFacultyStorage();
    }
    public static IFacultyStorage getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private Connection getConnection() {
        return DataSource.getInstance().getConnection();
    }


    @Override
    public Faculty saveFaculty(Faculty faculty) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "insert into faculty(faculty, Mark) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, faculty.getFaculty());
            statement.setInt(2, faculty.getMark());
            statement.executeUpdate();
            final long id;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                id = generatedKeys.getLong(1);
            }
            final Faculty facultyLog = new Faculty(id, faculty.getFaculty(), faculty.getMark());
            log.info("faculty saved: {}", facultyLog);
            return facultyLog;
        } catch (SQLException e) {
            log.error("fail to save faculty:{}", faculty, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Faculty> saveFaculty(List<Faculty> faculties) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("insert into faculty(faculty, Mark) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                for (Faculty faculty : faculties) {
                    statement.setString(1, faculty.getFaculty());
                    statement.setInt(2, faculty.getMark());
                    statement.addBatch();
                }
                statement.executeBatch();
                final ResultSet generatedKeys = statement.getGeneratedKeys();
                final ArrayList<Faculty> result = new ArrayList<>();
                for (Faculty faculty : faculties) {
                    generatedKeys.next();
                    final long id = generatedKeys.getLong(1);
                    result.add(new Faculty(id, faculty.getFaculty(), faculty.getMark()));
                }
                log.info("faculty saved: {}", result);
                connection.commit();
                return result;
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(e);
            }
            log.error("fail to save faculty:{}", faculties, e);
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
    public boolean deleteFaculty(long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("delete from faculty where id = ?")) {
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
    public boolean updateFaculty(Faculty faculty) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("update faculty set faculty = ?, Mark = ? where id = ?")) {
                statement.setString(1, faculty.getFaculty());
                statement.setInt(2, faculty.getMark());
                statement.setLong(3, faculty.getId());
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
    public Faculty getFaculty(long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("select * from faculty where id = ?")) {
                statement.setLong(1, id);
                final long resultId;
                final String faculty;
                final int mark;
                try (ResultSet resultSet = statement.executeQuery()) {
                    connection.commit();
                    final boolean exist = resultSet.next();
                    if (!exist) {
                        return null;
                    }
                    resultId = resultSet.getLong("id");
                    faculty = resultSet.getString("faculty");
                    mark = resultSet.getInt("Mark");
                }
                return new Faculty(resultId, faculty, mark);
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
    public List<Faculty> getFaculty() {
        try(Connection connection = getConnection();
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
}
