package it_academy.control_project.dao;

import it_academy.control_project.data.Applicant;

import java.util.List;

public interface IApplicantStorage {

    Applicant saveApplicant(Applicant applicant);
    List<Applicant> saveApplicant(List<Applicant> applicants);

    boolean deleteApplicant(long id);
    boolean updateApplicant(Applicant applicant);

    Applicant getApplicant(long id);
    List<Applicant> getApplicant();
}
