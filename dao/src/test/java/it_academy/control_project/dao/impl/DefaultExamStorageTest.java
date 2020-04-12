package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.IExamStorage;
import it_academy.control_project.data.Exam;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultExamStorageTest {

    IExamStorage examStorage = DefaultExamStorage.getInstance();

    @Test
    void saveExam() {
        final Exam examToSave = new Exam(null, 1L, "question", "answer");
        final Exam savedExam = examStorage.saveExam(examToSave);

        assertEquals(examToSave.getId(), savedExam.getId());
        assertEquals(examToSave.getFacultyId(), savedExam.getFacultyId());
        assertEquals(examToSave.getQuestion(), savedExam.getQuestion());
        assertEquals(examToSave.getAnswer(), savedExam.getAnswer());
    }

    @Test
    void deleteExam() {
        final Exam examToSave = new Exam(null, 1L, "question", "answer");
        final Exam savedExam = examStorage.saveExam(examToSave);
        final Long id = savedExam.getId();

        final Exam exam = examStorage.getExam(id);
        assertNotNull(exam);

        final boolean deleted = examStorage.deleteExam(id);
        assertTrue(deleted);

        final Exam afterDeleted = examStorage.getExam(id);
        assertNull(afterDeleted);
    }

    @Test
    void updateExam() {
        final Exam examToSave = new Exam(null, 1L, "question", "answer");
        final Exam savedExam = examStorage.saveExam(examToSave);
        final Long id = savedExam.getId();

        final Exam toUpdate = new Exam(null, 2L, "question2", "answer2");
        final boolean update = examStorage.updateExam(toUpdate);
        assertTrue(update);

        final Exam afterUpdate = examStorage.getExam(id);
        assertEquals(toUpdate.getId(), afterUpdate.getId());
        assertEquals(toUpdate.getFacultyId(), afterUpdate.getFacultyId());
        assertEquals(toUpdate.getQuestion(), afterUpdate.getQuestion());
        assertEquals(toUpdate.getAnswer(), afterUpdate.getAnswer());
    }

    @Test
    void getExam() {
        final Exam examToSave = new Exam(null, 1L, "question", "answer");
        final Exam savedExam = examStorage.saveExam(examToSave);
        final Long id = savedExam.getId();

        final Exam exam = examStorage.getExam(id);
        assertNotNull(exam);
        assertEquals(examToSave.getId(), exam.getId());
        assertEquals(examToSave.getFacultyId(), exam.getFacultyId());
        assertEquals(examToSave.getQuestion(), exam.getQuestion());
        assertEquals(examToSave.getAnswer(), exam.getAnswer());
    }
}