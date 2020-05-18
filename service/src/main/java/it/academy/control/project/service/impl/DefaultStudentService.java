package it.academy.control.project.service.impl;

import it.academy.control.project.dao.StudentDao;
import it.academy.control.project.dao.impl.DefaultStudentDao;
import it.academy.control.project.data.Student;
import it.academy.control.project.service.StudentService;

import java.util.List;

public class DefaultStudentService implements StudentService {

    private StudentDao studentDao = DefaultStudentDao.getInstance();

    private static class SingletonHolder{
        static final StudentService HOLDER_INSTANCE = new DefaultStudentService();
    }
    public static StudentService getInstance(){
        return DefaultStudentService.SingletonHolder.HOLDER_INSTANCE;
    }


    @Override
    public Student saveStudent(Student student) {
        return studentDao.saveStudent(student);
    }

    @Override
    public boolean deleteStudent(long id) {
        return studentDao.deleteStudent(id);
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public Student getStudent(long id) {
        return studentDao.getStudent(id);
    }

    @Override
    public List<Student> getStudents() {
        return studentDao.getStudents();
    }

    @Override
    public List<Student> getStudents(int number) {
        return studentDao.getStudents(number);
    }
}
