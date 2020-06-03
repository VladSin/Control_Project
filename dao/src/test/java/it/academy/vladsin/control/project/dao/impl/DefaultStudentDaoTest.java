package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.StudentDao;
import it.academy.vladsin.control.project.dao.util.HibernateUtil;
import it.academy.vladsin.control.project.data.Student;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultStudentDaoTest {

    StudentDao studentDao = DefaultStudentDao.getInstance();

    @Test
    void saveStudent() {
        final Student studentToSave = new Student(null, 1L, null);
        final Student savedStudent = studentDao.saveStudent(studentToSave);
        assertEquals(studentToSave.getUserId(), savedStudent.getUserId());
        assertEquals(studentToSave.getUniversities(), savedStudent.getUniversities());
        assertNotNull(savedStudent.getId());
    }

    @Test
    void deleteStudent() {
        final Student studentToSave = new Student(null, 1L, null);
        final Student savedStudent = studentDao.saveStudent(studentToSave);
        final Long id = savedStudent.getId();
        final Student student = studentDao.getStudent(id);
        assertNotNull(student);

        final boolean deleted = studentDao.deleteStudent(id);
        assertTrue(deleted);

        final Student afterDelete = studentDao.getStudent(id);
        assertNull(afterDelete);
    }

    @Test
    void updateStudent() {
        final Student studentToSave = new Student(null, 1L, null);
        final Student savedStudent = studentDao.saveStudent(studentToSave);
        final Long id = savedStudent.getId();

        final Student toUpdate = new Student(id, 2L, null);
        final boolean updated = studentDao.updateStudent(toUpdate);
        assertTrue(updated);

        final Student afterUpdate = studentDao.getStudent(id);
        assertEquals(toUpdate.getUserId(), afterUpdate.getUserId());
        assertEquals(toUpdate.getId(), afterUpdate.getId());
        assertEquals(toUpdate.getUniversities(), afterUpdate.getUniversities());
    }

    @Test
    void getList(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(null, 1L, null));
        students.add(new Student(null, 2L, null));

        List<Student> studentsList = new ArrayList<>();
        for (Student s: students) {
            studentsList.add(studentDao.saveStudent(s));
        }

        assertNotNull(studentsList);
        for (int i = 0; i < studentsList.size(); i++) {
            assertEquals(studentsList.get(i).getUserId(), students.get(i).getUserId());
            assertEquals(studentsList.get(i).getUniversities(), students.get(i).getUniversities());
        }
        students = studentDao.getStudents();
        assertNotNull(students);
    }

    @Test
    void getListNumber(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(null, 1L, null));
        students.add(new Student(null, 2L, null));

        List<Student> studentsList = new ArrayList<>();
        for (Student s: students) {
            studentsList.add(studentDao.saveStudent(s));
        }

        assertNotNull(studentsList);
        for (int i = 0; i < studentsList.size(); i++) {
            assertEquals(studentsList.get(i).getUserId(), students.get(i).getUserId());
            assertEquals(studentsList.get(i).getUniversities(), students.get(i).getUniversities());
        }
        students = studentDao.getStudents(1);
        assertNotNull(students);
    }

    @AfterClass
    public void cleanUp() {
        HibernateUtil.closeEMFactory();
    }
}
