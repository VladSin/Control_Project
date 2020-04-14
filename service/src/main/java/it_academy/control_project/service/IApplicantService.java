package it_academy.control_project.service;

import it_academy.control_project.data.Applicant;

import java.util.List;

public interface IApplicantService {

    Applicant saveApplicant(Applicant applicant);
    List<Applicant> saveApplicant(List<Applicant> applicants);

    boolean deleteApplicant(long id);
    boolean updateApplicant(Applicant applicant);

    Applicant getApplicant(long id);
    List<Applicant> getApplicant();
}
