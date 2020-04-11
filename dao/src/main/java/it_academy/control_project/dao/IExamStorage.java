package it_academy.control_project.dao;

import it_academy.control_project.data.Exam;

import java.util.List;

public interface IExamStorage {
    List<Exam> getExam();
    Long save(Exam exam);
}
