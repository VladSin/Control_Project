package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.IApplicantStorage;
import it_academy.control_project.data.Applicant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultApplicantStorage implements IApplicantStorage {

    private static final Logger log = LoggerFactory.getLogger(DefaultApplicantStorage.class);

    private static class SingletonHolder{
        static final IApplicantStorage HOLDER_INSTANCE = new DefaultApplicantStorage();
    }
    public static IApplicantStorage getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private Connection getConnection() {
        return DataSource.getInstance().getConnection();
    }

    @Override
    public Applicant saveApplicant(Applicant applicant) {
        try (PreparedStatement statement = getConnection().prepareStatement(
                "insert into applicant(user_id, faculty_id, mark) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, applicant.getUserId());
            statement.setLong(2, applicant.getFacultyId());
            statement.setInt(3, applicant.getMark());
            statement.executeUpdate();
            final long id;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                id = generatedKeys.getLong(1);
            }
            final Applicant applicantLog = new Applicant(id, applicant.getUserId(), applicant.getFacultyId(), applicant.getMark());
            log.info("applicant saved: {}", applicantLog);
            return applicantLog;
        } catch (SQLException e) {
            log.error("fail to save applicant:{}", applicant, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Applicant> saveApplicant(List<Applicant> applicants) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("insert into applicant(user_id, faculty_id, mark) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                for (Applicant applicant : applicants) {
                    statement.setLong(1, applicant.getUserId());
                    statement.setLong(2, applicant.getFacultyId());
                    statement.setInt(3, applicant.getMark());
                    statement.addBatch();
                }
                statement.executeBatch();
                final ResultSet generatedKeys = statement.getGeneratedKeys();
                final ArrayList<Applicant> result = new ArrayList<>();
                for (Applicant applicant : applicants) {
                    generatedKeys.next();
                    final long id = generatedKeys.getLong(1);
                    result.add(new Applicant(id, applicant.getUserId(), applicant.getFacultyId(), applicant.getMark()));
                }
                log.info("applicant saved: {}", result);
                connection.commit();
                return result;
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(e);
            }
            log.error("fail to save applicant:{}", applicants, e);
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
    public boolean deleteApplicant(long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("delete from applicant where id = ?")) {
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
    public boolean updateApplicant(Applicant applicant) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("update applicant set user_id = ?, faculty_id = ?, mark = ? where id = ?")) {
                statement.setLong(1, applicant.getUserId());
                statement.setLong(2, applicant.getFacultyId());
                statement.setInt(3, applicant.getMark());
                statement.setLong(4, applicant.getId());
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
    public Applicant getApplicant(long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("select * from applicant where id = ?")) {
                statement.setLong(1, id);
                final ResultSet resultSet = statement.executeQuery();
                connection.commit();
                final boolean exist = resultSet.next();
                if (!exist) {
                    return null;
                }
                final long resultId = resultSet.getLong("id");
                final long userId = resultSet.getLong("user_id");
                final long facultyId = resultSet.getLong("faculty_id");
                final int mark = resultSet.getInt("mark");
                return new Applicant(resultId, userId, facultyId, mark);
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
    public List<Applicant> getApplicant() {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from applicant");
             ResultSet resultSet = preparedStatement.executeQuery()){

            final ArrayList<Applicant> result = new ArrayList<>();
            while (resultSet.next()){
                final Applicant applicant = new Applicant(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getLong("faculty_id"),
                        resultSet.getInt("mark"));
                result.add(applicant);
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
