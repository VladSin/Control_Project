package it_academy.control_project.service.impl;

import it_academy.control_project.dao.impl.DefaultApplicantStorage;
import it_academy.control_project.data.Applicant;
import it_academy.control_project.service.IApplicantService;

import java.util.List;

public class DefaultApplicantService implements IApplicantService {

    private static class SingletonHolder {
        static final IApplicantService HOLDER_INSTANCE = new DefaultApplicantService();
    }
    public static IApplicantService getInstance() {
        return DefaultApplicantService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Applicant saveApplicant(Applicant applicant) {
        return DefaultApplicantStorage.getInstance().saveApplicant(applicant);
    }

    @Override
    public List<Applicant> saveApplicant(List<Applicant> applicants) {
        return DefaultApplicantStorage.getInstance().saveApplicant(applicants);
    }

    @Override
    public boolean deleteApplicant(long id) {
        return DefaultApplicantStorage.getInstance().deleteApplicant(id);
    }

    @Override
    public boolean updateApplicant(Applicant applicant) {
        return DefaultApplicantStorage.getInstance().updateApplicant(applicant);
    }

    @Override
    public Applicant getApplicant(long id) {
        return DefaultApplicantStorage.getInstance().getApplicant(id);
    }

    @Override
    public List<Applicant> getApplicant() {
        return DefaultApplicantStorage.getInstance().getApplicant();
    }

}
