package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.StudentDao;
import it.academy.vladsin.control.project.data.Student;
import it.academy.vladsin.control.project.service.StudentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultStudentService implements StudentService {

    private final StudentDao studentDao;

    public DefaultStudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        return studentDao.saveStudent(student);
    }

    @Override
    @Transactional
    public boolean deleteStudent(long id) {
        return studentDao.deleteStudent(id);
    }

    @Override
    @Transactional
    public boolean updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    @Transactional
    public Student getStudent(long id) {
        return studentDao.getStudent(id);
    }

    @Override
    @Transactional
    public List<Student> getStudents() {
        return studentDao.getStudents();
    }

    @Override
    @Transactional
    public List<Student> getStudents(int number) {
        return studentDao.getStudents(number);
    }
}
