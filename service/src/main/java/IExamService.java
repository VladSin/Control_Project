import it_academy.control_project.data.university.Exam;

import java.util.List;

public interface IExamService {

    List<Exam> getExam();
    Long saveExam(Exam exam);
}
