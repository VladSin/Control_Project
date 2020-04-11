package it_academy.control_project.service;

import it_academy.control_project.data.Applicant;

import java.util.List;

public interface IApplicantService {

    List<Applicant> getApplicant();
    Long saveApplicant(Applicant applicant);
}
