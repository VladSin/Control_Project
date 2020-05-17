package it.academy.control.project.service.impl;

import it.academy.control.project.dao.FacultyDao;
import it.academy.control.project.data.Exam;
import it.academy.control.project.data.Faculty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultFacultyServiceTest {

    @Mock
    FacultyDao dao;

    @InjectMocks
    DefaultFacultyService service;

    @Test
    void getNonExistentUser(){
        when(dao.getFaculty(0)).thenReturn(null);
        Faculty faculty = service.getFaculty(0);
        assertNull(faculty);
    }

    @Test
    void getExistingFaculty(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);
        assertEquals(facultyFromDb.getFaculty(), "faculty");
        assertEquals(facultyFromDb.getMark(), 10);
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getFaculty(2)).thenReturn(null);
        Faculty facultyFromDb = service.getFaculty(2);
        assertNull(facultyFromDb);
    }

    @Test
    void getFaculty(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        final Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);

        final Faculty faculty = service.getFaculty(1);
        assertNotNull(faculty);
        assertEquals(facultyFromDb.getId(), faculty.getId());
        assertEquals(facultyFromDb.getFaculty(), faculty.getFaculty());
        assertEquals(facultyFromDb.getMark(), faculty.getMark());
    }

    @Test
    void saveFaculty(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        final Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);

        when(dao.saveFaculty(facultyFromDb)).thenReturn(facultyFromDb);
        final Faculty faculty = service.saveFaculty(facultyFromDb);

        assertNotNull(faculty);
        assertEquals(facultyFromDb.getId(), faculty.getId());
        assertEquals(facultyFromDb.getMark(), faculty.getMark());
        assertEquals(facultyFromDb.getFaculty(), faculty.getFaculty());
    }

    @Test
    void deleteFaculty(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        final Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);

        when(dao.deleteFaculty(1L)).thenReturn(true);
        final boolean delete = service.deleteFaculty(1L);
        assertTrue(delete);
    }

    @Test
    void updateFaculty(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        final Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);

        when(dao.updateFaculty(facultyFromDb)).thenReturn(true);
        final boolean update = service.updateFaculty(facultyFromDb);
        assertTrue(update);
    }

    @Test
    void getList() {
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty(1L, "Faculty1", 10));
        faculties.add(new Faculty(2L, "Faculty2", 10));
        when(dao.getFaculties()).thenReturn(faculties);

        List<Faculty> facultiesDao = dao.getFaculties();
        assertNotNull(facultiesDao);
        for (int i = 0; i < facultiesDao.size(); i++) {
            assertEquals(facultiesDao.get(i).getId(), faculties.get(i).getId());
            assertEquals(facultiesDao.get(i).getFaculty(), faculties.get(i).getFaculty());
            assertEquals(facultiesDao.get(i).getMark(), faculties.get(i).getMark());
        }
    }

    @Test
    void getExam(){
        List<Exam> exams = new ArrayList<>();
        exams.add(new Exam(1L, 1L, "question1", "answer1"));
        exams.add(new Exam(2L, 2L, "question2", "answer2"));
        when(dao.getExamForFaculty()).thenReturn(exams);

        List<Exam> examsDao = dao.getExamForFaculty();
        assertNotNull(examsDao);
        for (int i = 0; i < examsDao.size(); i++) {
            assertEquals(examsDao.get(i).getId(), exams.get(i).getId());
            assertEquals(examsDao.get(i).getFacultyId(), exams.get(i).getFacultyId());
            assertEquals(examsDao.get(i).getQuestion(), exams.get(i).getQuestion());
            assertEquals(examsDao.get(i).getAnswer(), exams.get(i).getAnswer());
        }
    }
}