import it_academy.control_project.data.university.Exam;

import java.util.List;

public class DefaultExamService implements IExamService {

    private static class SingletonHolder {
        static final IExamService HOLDER_INSTANCE = new DefaultExamService();
    }

    public static IExamService getInstance() {
        return DefaultExamService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public List<Exam> getExam() {
        return DefaultExamStorage.getInstance().getExam();
    }

    @Override
    public Long saveExam(Exam exam) {
        return DefaultExamStorage.getInstance().save(exam);
    }
}
