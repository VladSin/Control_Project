package it_academy.control_project.service;

import it_academy.control_project.data.Applicant;

import java.util.List;

public interface ApplicantService {

    Applicant saveApplicant(Applicant applicant);

    boolean deleteApplicant(long id);

    boolean updateApplicant(Applicant applicant);

    Applicant getApplicant(long id);

    List<Applicant> getApplicant();
}
