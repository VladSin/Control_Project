package it.academy.control.project.service.impl;

import it.academy.control.project.dao.ExamDao;
import it.academy.control.project.data.Exam;
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
class DefaultExamServiceTest {

    @Mock
    ExamDao dao;

    @InjectMocks
    DefaultExamService service;

    @Test
    void getNonExistentExam(){
        when(dao.getExam(0)).thenReturn(null);
        Exam exam = service.getExam(0);
        assertNull(exam);
    }

    @Test
    void getExistingExam(){
        when(dao.getExam(1)).thenReturn(new Exam(1L, 1L, "question", "answer"));
        Exam examFromDb = service.getExam(1);
        assertNotNull(examFromDb);
        assertEquals(examFromDb.getQuestion(), "question");
        assertEquals(examFromDb.getAnswer(), "answer");
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getExam(2)).thenReturn(null);
        Exam examFromDb = service.getExam(2);
        assertNull(examFromDb);
    }

    @Test
    void getExam(){
        when(dao.getExam(1)).thenReturn(new Exam(1L, 1L, "question", "answer"));
        Exam examFromDb = service.getExam(1);
        assertNotNull(examFromDb);

        final Exam exam = service.getExam(1);
        assertNotNull(exam);
        assertEquals(examFromDb.getId(), exam.getId());
        assertEquals(examFromDb.getFacultyId(), exam.getFacultyId());
        assertEquals(examFromDb.getQuestion(), exam.getQuestion());
        assertEquals(examFromDb.getAnswer(), exam.getAnswer());
    }

    @Test
    void saveExam(){
        when(dao.getExam(1)).thenReturn(new Exam(1L, 1L, "question", "answer"));
        Exam examFromDb = service.getExam(1);
        assertNotNull(examFromDb);

        when(dao.saveExam(examFromDb)).thenReturn(examFromDb);
        final Exam exam = service.saveExam(examFromDb);

        assertNotNull(exam);
        assertEquals(examFromDb.getId(), exam.getId());
        assertEquals(examFromDb.getFacultyId(), exam.getFacultyId());
        assertEquals(examFromDb.getQuestion(), exam.getQuestion());
        assertEquals(examFromDb.getAnswer(), exam.getAnswer());
    }

    @Test
    void deleteExam(){
        when(dao.getExam(1)).thenReturn(new Exam(1L, 1L, "question", "answer"));
        Exam examFromDb = service.getExam(1);
        assertNotNull(examFromDb);

        when(dao.deleteExam(1L)).thenReturn(true);
        final boolean delete = service.deleteExam(1L);
        assertTrue(delete);
    }

    @Test
    void updateExam(){
        when(dao.getExam(1)).thenReturn(new Exam(1L, 1L, "question", "answer"));
        Exam examFromDb = service.getExam(1);
        assertNotNull(examFromDb);

        when(dao.updateExam(examFromDb)).thenReturn(true);
        final boolean update = service.updateExam(examFromDb);
        assertTrue(update);
    }

    @Test
    void getList() {
        List<Exam> exams = new ArrayList<>();
        exams.add(new Exam(1L, 1L,"question1", "answer1"));
        exams.add(new Exam(2L, 2L,"question2", "answer2"));
        when(dao.getExams()).thenReturn(exams);

        List<Exam> examsDao = dao.getExams();
        assertNotNull(examsDao);
        for (int i = 0; i < examsDao.size(); i++) {
            assertEquals(examsDao.get(i).getId(), exams.get(i).getId());
            assertEquals(examsDao.get(i).getFacultyId(), exams.get(i).getFacultyId());
            assertEquals(examsDao.get(i).getQuestion(), exams.get(i).getQuestion());
            assertEquals(examsDao.get(i).getAnswer(), exams.get(i).getAnswer());
        }
    }

    @Test
    void getListFaculty() {
        List<Exam> exams = new ArrayList<>();
        exams.add(new Exam(1L, 1L,"question1", "answer1"));
        exams.add(new Exam(2L, 1L,"question2", "answer2"));
        when(dao.getExams(1L)).thenReturn(exams);

        List<Exam> examsDao = dao.getExams(1L);
        assertNotNull(examsDao);
        for (int i = 0; i < examsDao.size(); i++) {
            assertEquals(examsDao.get(i).getId(), exams.get(i).getId());
            assertEquals(examsDao.get(i).getFacultyId(), exams.get(i).getFacultyId());
            assertEquals(examsDao.get(i).getQuestion(), exams.get(i).getQuestion());
            assertEquals(examsDao.get(i).getAnswer(), exams.get(i).getAnswer());
        }
    }

    @Test
    void getListNumber() {
        List<Exam> exams = new ArrayList<>();
        exams.add(new Exam(1L, 1L,"question1", "answer1"));
        exams.add(new Exam(2L, 2L,"question2", "answer2"));
        when(dao.getExams(2)).thenReturn(exams);

        List<Exam> examsDao = dao.getExams(2);
        assertNotNull(examsDao);
        for (int i = 0; i < examsDao.size(); i++) {
            assertEquals(examsDao.get(i).getId(), exams.get(i).getId());
            assertEquals(examsDao.get(i).getFacultyId(), exams.get(i).getFacultyId());
            assertEquals(examsDao.get(i).getQuestion(), exams.get(i).getQuestion());
            assertEquals(examsDao.get(i).getAnswer(), exams.get(i).getAnswer());
        }
    }
}