package it.academy.vladsin.control.project.dao;

import it.academy.vladsin.control.project.data.Applicant;

import java.util.List;

public interface ApplicantDao {

    Applicant saveApplicant(Applicant applicant);

    boolean deleteApplicant(long id);

    boolean updateApplicant(Applicant applicant);

    Applicant getApplicant(long id);

    List<Applicant> getApplicants();

    List<Applicant> getApplicants(int number);
}
