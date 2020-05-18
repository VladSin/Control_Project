package it.academy.control.project.service;

import it.academy.control.project.data.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    boolean deleteStudent(long id);

    boolean updateStudent(Student student);

    Student getStudent(long id);

    List<Student> getStudents();

    List<Student> getStudents(int number);
}
