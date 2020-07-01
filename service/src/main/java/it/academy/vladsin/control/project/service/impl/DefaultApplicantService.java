package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.ApplicantDao;
import it.academy.vladsin.control.project.data.Applicant;
import it.academy.vladsin.control.project.service.ApplicantService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultApplicantService implements ApplicantService {

    private final ApplicantDao applicantDao;

    public DefaultApplicantService(ApplicantDao applicantDao) {
        this.applicantDao = applicantDao;
    }

    @Override
    @Transactional
    public Applicant saveApplicant(Applicant applicant) {
        return applicantDao.saveApplicant(applicant);
    }

    @Override
    @Transactional
    public boolean deleteApplicant(long id) {
        return applicantDao.deleteApplicant(id);
    }

    @Override
    @Transactional
    public boolean updateApplicant(Applicant applicant) {
        return applicantDao.updateApplicant(applicant);
    }

    @Override
    @Transactional
    public Applicant getApplicant(long id) {
        return applicantDao.getApplicant(id);
    }

    @Override
    @Transactional
    public List<Applicant> getApplicants() {
        return applicantDao.getApplicants();
    }

    @Override
    @Transactional
    public List<Applicant> getApplicants(int number) {
        return applicantDao.getApplicants(number);
    }
}
