package it_academy.control_project.service.impl;

import it_academy.control_project.dao.ApplicantDao;
import it_academy.control_project.dao.impl.DefaultApplicantDao;
import it_academy.control_project.data.Applicant;
import it_academy.control_project.service.ApplicantService;

import java.util.List;

public class DefaultApplicantService implements ApplicantService {

    private ApplicantDao applicantDao = DefaultApplicantDao.getInstance();

    private static class SingletonHolder {
        static final ApplicantService HOLDER_INSTANCE = new DefaultApplicantService();
    }
    public static ApplicantService getInstance() {
        return DefaultApplicantService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Applicant saveApplicant(Applicant applicant) {
        return applicantDao.saveApplicant(applicant);
    }

    @Override
    public boolean deleteApplicant(long id) {
        return applicantDao.deleteApplicant(id);
    }

    @Override
    public boolean updateApplicant(Applicant applicant) {
        return applicantDao.updateApplicant(applicant);
    }

    @Override
    public Applicant getApplicant(long id) {
        return applicantDao.getApplicant(id);
    }

    @Override
    public List<Applicant> getApplicant() {
        return applicantDao.getApplicant();
    }
}
