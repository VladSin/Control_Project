package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.IExamStorage;
import it_academy.control_project.data.Exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultExamStorage implements IExamStorage {

    private static final Logger log = LoggerFactory.getLogger(DefaultExamStorage.class);

    private static class SingletonHolder{
        static final IExamStorage HOLDER_INSTANCE = new DefaultExamStorage();
    }
    public static IExamStorage getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private Connection getConnection() {
        return DataSource.getInstance().getConnection();
    }

    @Override
    public Exam saveExam(Exam exam) {
        try (PreparedStatement statement = getConnection().prepareStatement(
                "insert into test(faculty_id, question, answer) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, exam.getFacultyId());
            statement.setString(2, exam.getQuestion());
            statement.setString(3, exam.getAnswer());
            statement.executeUpdate();
            final long id;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                id = generatedKeys.getLong(1);
            }
            final Exam examLog = new Exam(id, exam.getFacultyId(), exam.getQuestion(), exam.getAnswer());
            log.info("exam saved: {}", examLog);
            return examLog;
        } catch (SQLException e) {
            log.error("fail to save exam:{}", exam, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Exam> saveExam(List<Exam> exams) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("insert into test(faculty_id, question, answer) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                for (Exam exam : exams) {
                    statement.setLong(1, exam.getFacultyId());
                    statement.setString(2, exam.getQuestion());
                    statement.setString(3, exam.getAnswer());
                    statement.addBatch();
                }
                statement.executeBatch();
                final ResultSet generatedKeys = statement.getGeneratedKeys();
                final ArrayList<Exam> result = new ArrayList<>();
                for (Exam exam : exams) {
                    generatedKeys.next();
                    final long id = generatedKeys.getLong(1);
                    result.add(new Exam(id, exam.getFacultyId(), exam.getQuestion(), exam.getAnswer()));
                }
                log.info("exam saved: {}", result);
                connection.commit();
                return result;
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(e);
            }
            log.error("fail to save exam:{}", exams, e);
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
    public boolean deleteExam(long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("delete from exam where id = ?")) {
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
    public boolean updateExam(Exam exam) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("update exam set faculty_id = ?, question = ?, answer = ? where id = ?")) {
                statement.setLong(1, exam.getFacultyId());
                statement.setString(2, exam.getQuestion());
                statement.setString(3, exam.getAnswer());
                statement.setLong(4, exam.getId());
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
    public Exam getExam(long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("select * from exam where id = ?")) {
                statement.setLong(1, id);
                final ResultSet resultSet = statement.executeQuery();
                connection.commit();
                final boolean exist = resultSet.next();
                if (!exist) {
                    return null;
                }
                final long resultId = resultSet.getLong("id");
                final long facultyId = resultSet.getInt("faculty_id");
                final String question = resultSet.getString("question");
                final String answer = resultSet.getString("answer");
                return new Exam(resultId, facultyId, question, answer);
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
    public List<Exam> getExam() {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from test");
            ResultSet resultSet = preparedStatement.executeQuery()) {

            final ArrayList<Exam> result = new ArrayList<>();
            while (resultSet.next()){
                final Exam exam = new Exam(
                        resultSet.getLong("id"),
                        resultSet.getLong("faculty_id"),
                        resultSet.getString("question"),
                        resultSet.getString("answer"));
                result.add(exam);
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
