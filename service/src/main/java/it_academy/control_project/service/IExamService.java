package it_academy.control_project.service;

import it_academy.control_project.data.Exam;

import java.util.List;

public interface IExamService {
    Exam saveExam(Exam exam);
    List<Exam> saveExam(List<Exam> exams);

    boolean deleteExam(long id);
    boolean updateExam(Exam exam);

    Exam getExam(long id);
    List<Exam> getExam();
}
