import it_academy.control_project.data.university.Exam;

import java.util.List;

public interface IExamStorage {
    List<Exam> getExam();
    Long save(Exam exam);
}
