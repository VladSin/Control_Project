package it.academy.vladsin.control.project.dao;

import it.academy.vladsin.control.project.data.Student;

import java.util.List;

public interface StudentDao {

    Student saveStudent(Student student);

    boolean deleteStudent(long id);

    boolean updateStudent(Student student);

    Student getStudent(long id);

    List<Student> getStudents();

    List<Student> getStudents(int number);
}
