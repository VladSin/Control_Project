package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.IExamStorage;
import it_academy.control_project.data.Exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultExamStorage implements IExamStorage {

    private static class SingletonHolder{
        static final IExamStorage HOLDER_INSTANCE = new DefaultExamStorage();
    }
    public static IExamStorage getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
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

    @Override
    public Long save(Exam exam) {
        final String sql = "insert into test(faculty_id, question, answer) values(?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setLong(1, exam.getFacultyId());
            preparedStatement.setString(2, exam.getQuestion());
            preparedStatement.setString(3, exam.getAnswer());
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
