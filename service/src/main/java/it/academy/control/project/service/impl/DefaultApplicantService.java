package it.academy.control.project.service.impl;

import it.academy.control.project.dao.ApplicantDao;
import it.academy.control.project.dao.impl.DefaultApplicantDao;
import it.academy.control.project.data.Applicant;
import it.academy.control.project.service.ApplicantService;

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
