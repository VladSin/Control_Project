package it_academy.control_project.service;

import it_academy.control_project.data.Exam;

import java.util.List;

public interface IExamService {

    List<Exam> getExam();
    Long saveExam(Exam exam);
}
