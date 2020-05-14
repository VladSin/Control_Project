package it_academy.control_project.dao;

import it_academy.control_project.data.Exam;

import java.util.List;

public interface ExamDao {

    Exam saveExam(Exam exam);

    boolean deleteExam(long id);

    boolean updateExam(Exam exam);

    Exam getExam(long id);

    List<Exam> getExam();
}
