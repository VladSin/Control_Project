package it_academy.control_project.dao;

import it_academy.control_project.data.Applicant;

import java.util.List;

public interface ApplicantDao {

    Applicant saveApplicant(Applicant applicant);

    boolean deleteApplicant(long id);

    boolean updateApplicant(Applicant applicant);

    Applicant getApplicant(long id);

    List<Applicant> getApplicant();
}
