package it_academy.control_project.dao;

import it_academy.control_project.data.Applicant;

import java.util.List;

public interface IApplicantStorage {
    List<Applicant> getApplicant();
    Long save(Applicant applicant);
}
