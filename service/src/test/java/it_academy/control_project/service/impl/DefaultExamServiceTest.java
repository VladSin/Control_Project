package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IExamStorage;
import it_academy.control_project.data.Exam;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DefaultExamServiceTest {

    @Mock
    IExamStorage dao;

    @InjectMocks
    DefaultExamService service;

    @Test
    void getNonExistentUser(){
        when(dao.getExam(0)).thenReturn(null);
        Exam exam = service.getExam(0);
        assertNull(exam);
    }

    @Test
    void getExistingUser(){
        when(dao.getExam(1)).thenReturn(new Exam(1L, 1L, "question", "answer"));
        Exam examFromDb = service.getExam(1);
        assertNotNull(examFromDb);
        assertEquals(examFromDb.getQuestion(), "question");
        assertEquals(examFromDb.getAnswer(), "answer");
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getExam(1)).thenReturn(new Exam(1L, 1L, "question", "answer"));
        Exam examFromDb = service.getExam(2);
        assertNull(examFromDb);
    }

    @Test
    void deleteUser(){
        when(dao.getExam(1)).thenReturn(new Exam(1L, 1L, "question", "answer"));
        Exam examFromDb = service.getExam(1);
        assertNotNull(examFromDb);

        final boolean deleted = service.deleteExam(1);
        assertTrue(deleted);

        final Exam afterDeleted = service.getExam(1);
        assertNull(afterDeleted);
    }

    @Test
    void getUser(){
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

}