package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.ExamDao;
import it_academy.control_project.data.Exam;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultExamDaoTest {

    ExamDao examDao = DefaultExamDao.getInstance();

    @Test
    void saveExam() {
        final Exam examToSave = new Exam(null, 1L, "question", "answer");
        final Exam savedExam = examDao.saveExam(examToSave);
        assertEquals(examToSave.getFacultyId(), savedExam.getFacultyId());
        assertEquals(examToSave.getQuestion(), savedExam.getQuestion());
        assertEquals(examToSave.getAnswer(), savedExam.getAnswer());
    }

    @Test
    void deleteExam() {
        final Exam examToSave = new Exam(null, 1L, "question", "answer");
        final Exam savedExam = examDao.saveExam(examToSave);
        final Long id = savedExam.getId();

        final Exam exam = examDao.getExam(id);
        assertNotNull(exam);

        final boolean deleted = examDao.deleteExam(id);
        assertTrue(deleted);

        final Exam afterDeleted = examDao.getExam(id);
        assertNull(afterDeleted);
    }

    @Test
    void updateExam() {
        final Exam examToSave = new Exam(null, 1L, "question", "answer");
        final Exam savedExam = examDao.saveExam(examToSave);
        final Long id = savedExam.getId();

        final Exam toUpdate = new Exam(id, 2L, "question2", "answer2");
        final boolean update = examDao.updateExam(toUpdate);
        assertTrue(update);

        final Exam afterUpdate = examDao.getExam(id);

        assertEquals(toUpdate.getFacultyId(), afterUpdate.getFacultyId());
        assertEquals(toUpdate.getQuestion(), afterUpdate.getQuestion());
        assertEquals(toUpdate.getAnswer(), afterUpdate.getAnswer());
    }

    @Test
    void getExam() {
        final Exam examToSave = new Exam(null, 1L, "question", "answer");
        final Exam savedExam = examDao.saveExam(examToSave);
        final Long id = savedExam.getId();

        final Exam exam = examDao.getExam(id);
        assertNotNull(exam);

        assertEquals(examToSave.getFacultyId(), exam.getFacultyId());
        assertEquals(examToSave.getQuestion(), exam.getQuestion());
        assertEquals(examToSave.getAnswer(), exam.getAnswer());
    }
}