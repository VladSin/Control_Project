import it_academy.control_project.data.university.Applicant;

import java.util.List;

public class DefaultApplicantService implements IApplicantService{

    private static class SingletonHolder {
        static final IApplicantService HOLDER_INSTANCE = new DefaultApplicantService();
    }

    public static IApplicantService getInstance() {
        return DefaultApplicantService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public List<Applicant> getApplicant() {
        return DefaultApplicantStorage.getInstance().getApplicant();
    }

    @Override
    public Long saveApplicant(Applicant applicant) {
        return DefaultApplicantStorage.getInstance().save(applicant);
    }
}
