package it.academy.control.project.dao;

import it.academy.control.project.data.Exam;

import java.util.List;

public interface ExamDao {

    Exam saveExam(Exam exam);

    boolean deleteExam(long id);

    boolean updateExam(Exam exam);

    Exam getExam(long id);

    List<Exam> getExam();

    List<Exam> getExam(int number);
}
