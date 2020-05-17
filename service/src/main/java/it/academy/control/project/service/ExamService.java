package it.academy.control.project.service;

import it.academy.control.project.data.Exam;

import java.util.List;

public interface ExamService {

    Exam saveExam(Exam exam);

    boolean deleteExam(long id);

    boolean updateExam(Exam exam);

    Exam getExam(long id);

    List<Exam> getExams();

    List<Exam> getExams(Long facultyId);

    List<Exam> getExams(int number);
}
