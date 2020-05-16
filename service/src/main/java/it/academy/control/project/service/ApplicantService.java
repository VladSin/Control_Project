package it.academy.control.project.service;

import it.academy.control.project.data.Applicant;

import java.util.List;

public interface ApplicantService {

    Applicant saveApplicant(Applicant applicant);

    boolean deleteApplicant(long id);

    boolean updateApplicant(Applicant applicant);

    Applicant getApplicant(long id);

    List<Applicant> getApplicant();
}
