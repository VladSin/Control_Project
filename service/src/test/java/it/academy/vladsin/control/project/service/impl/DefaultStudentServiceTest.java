package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.StudentDao;
import it.academy.vladsin.control.project.data.Student;
import it.academy.vladsin.control.project.data.University;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultStudentServiceTest {

    @Mock
    StudentDao dao;

    @InjectMocks
    DefaultStudentService service;

    @Test
    void getNonExistentApplicant(){
        when(dao.getStudent(0L)).thenReturn(null);
        Student student = service.getStudent(0L);
        assertNull(student);
    }

    @Test
    void getExistingApplicant(){
        List<University> universities = new ArrayList<>();
        when(dao.getStudent(1L)).thenReturn(new Student(1L, 1L, universities));
        Student studentFromDb = service.getStudent(1L);
        assertNotNull(studentFromDb);
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getStudent(2L)).thenReturn(null);
        Student studentFromDb = service.getStudent(2L);
        assertNull(studentFromDb);
    }

    @Test
    void getApplicant(){
        List<University> universities = new ArrayList<>();
        when(dao.getStudent(1L)).thenReturn(new Student(1L, 1L, universities));
        Student studentFromDb = service.getStudent(1L);
        assertNotNull(studentFromDb);

        final Student student = service.getStudent(1L);
        assertNotNull(student);
        assertEquals(studentFromDb.getId(), student.getId());
        assertEquals(studentFromDb.getUserId(), student.getUserId());
        assertEquals(studentFromDb.getUniversities(), student.getUniversities());
    }

    @Test
    void saveApplicant(){
        List<University> universities = new ArrayList<>();
        when(dao.getStudent(1L)).thenReturn(new Student(1L, 1L, universities));
        Student studentFromDb = service.getStudent(1L);
        assertNotNull(studentFromDb);

        when(dao.saveStudent(studentFromDb)).thenReturn(studentFromDb);
        final Student student = service.saveStudent(studentFromDb);

        assertNotNull(student);
        assertNotNull(student);
        assertEquals(studentFromDb.getId(), student.getId());
        assertEquals(studentFromDb.getUserId(), student.getUserId());
        assertEquals(studentFromDb.getUniversities(), student.getUniversities());
    }

    @Test
    void deleteApplicant(){
        List<University> universities = new ArrayList<>();
        when(dao.getStudent(1L)).thenReturn(new Student(1L, 1L, universities));
        Student studentFromDb = service.getStudent(1L);
        assertNotNull(studentFromDb);

        when(dao.deleteStudent(1L)).thenReturn(true);
        final boolean delete = service.deleteStudent(1L);
        assertTrue(delete);
    }

    @Test
    void updateApplicant(){
        List<University> universities = new ArrayList<>();
        when(dao.getStudent(1L)).thenReturn(new Student(1L, 1L, universities));
        Student studentFromDb = service.getStudent(1L);
        assertNotNull(studentFromDb);

        when(dao.updateStudent(studentFromDb)).thenReturn(true);
        final boolean update = service.updateStudent(studentFromDb);
        assertTrue(update);
    }

    @Test
    void getList() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, 1L, null));
        students.add(new Student(2L, 2L, null));
        when(dao.getStudents()).thenReturn(students);

        List<Student> studentDao = dao.getStudents();
        assertNotNull(studentDao);
        for (int i = 0; i < studentDao.size(); i++) {
            assertEquals(studentDao.get(i).getId(), students.get(i).getId());
            assertEquals(studentDao.get(i).getUserId(), students.get(i).getUserId());
            assertEquals(studentDao.get(i).getUniversities(), students.get(i).getUniversities());
        }
    }

    @Test
    void getListNumber() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, 1L, null));
        students.add(new Student(2L, 2L, null));
        when(dao.getStudents(2)).thenReturn(students);

        List<Student> studentDao = dao.getStudents(2);
        assertNotNull(studentDao);
        for (int i = 0; i < studentDao.size(); i++) {
            assertEquals(studentDao.get(i).getId(), students.get(i).getId());
            assertEquals(studentDao.get(i).getUserId(), students.get(i).getUserId());
            assertEquals(studentDao.get(i).getUniversities(), students.get(i).getUniversities());
        }
    }
}
