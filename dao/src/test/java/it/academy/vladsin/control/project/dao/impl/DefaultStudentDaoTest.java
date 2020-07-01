package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.StudentDao;
import it.academy.vladsin.control.project.dao.config.DaoConfig;
import it.academy.vladsin.control.project.data.Student;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
@Commit
public class DefaultStudentDaoTest {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    SessionFactory sessionFactory;

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
        sessionFactory.getCurrentSession().clear();

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
        sessionFactory.getCurrentSession().clear();

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
}
