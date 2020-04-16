package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IApplicantStorage;
import it_academy.control_project.dao.impl.DefaultApplicantStorage;
import it_academy.control_project.data.Applicant;
import it_academy.control_project.service.IApplicantService;

import java.util.List;

public class DefaultApplicantService implements IApplicantService {

    private IApplicantStorage applicantStorage = DefaultApplicantStorage.getInstance();

    private static class SingletonHolder {
        static final IApplicantService HOLDER_INSTANCE = new DefaultApplicantService();
    }
    public static IApplicantService getInstance() {
        return DefaultApplicantService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Applicant saveApplicant(Applicant applicant) {
        return applicantStorage.saveApplicant(applicant);
    }

    @Override
    public List<Applicant> saveApplicant(List<Applicant> applicants) {
        return applicantStorage.saveApplicant(applicants);
    }

    @Override
    public boolean deleteApplicant(long id) {
        return applicantStorage.deleteApplicant(id);
    }

    @Override
    public boolean updateApplicant(Applicant applicant) {
        return applicantStorage.updateApplicant(applicant);
    }

    @Override
    public Applicant getApplicant(long id) {
        return applicantStorage.getApplicant(id);
    }

    @Override
    public List<Applicant> getApplicant() {
        return applicantStorage.getApplicant();
    }
}
