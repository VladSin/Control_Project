import it_academy.control_project.data.university.Applicant;

import java.util.List;

public interface IApplicantStorage {
    List<Applicant> getApplicant();
    Long save(Applicant applicant);
}
