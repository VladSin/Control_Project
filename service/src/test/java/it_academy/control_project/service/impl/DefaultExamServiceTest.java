package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IExamStorage;
import it_academy.control_project.data.Exam;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultExamServiceTest {

    @Mock
    IExamStorage dao;

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
}