import it_academy.control_project.data.university.Applicant;

import java.util.List;

public interface IApplicantService {

    List<Applicant> getApplicant();
    Long saveApplicant(Applicant applicant);
}
