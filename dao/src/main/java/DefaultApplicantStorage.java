import it_academy.control_project.data.university.Applicant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultApplicantStorage implements IApplicantStorage{

    private static class SingletonHolder{
        static final IApplicantStorage HOLDER_INSTANCE = new DefaultApplicantStorage();
    }
    public static IApplicantStorage getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
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

    @Override
    public Long save(Applicant applicant) {
        final String sql = "insert into applicant(user_id, faculty_id, mark) values(?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setLong(1, applicant.getUserId());
            preparedStatement.setLong(2, applicant.getFacultyId());
            preparedStatement.setInt(3, applicant.getMark());
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
