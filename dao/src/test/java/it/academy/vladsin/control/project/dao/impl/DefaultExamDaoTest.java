package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.ExamDao;
import it.academy.vladsin.control.project.dao.config.DaoConfig;
import it.academy.vladsin.control.project.data.Exam;
import it.academy.vladsin.control.project.data.Faculty;
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
class DefaultExamDaoTest {

    @Autowired
    private ExamDao examDao;

    @Autowired
    SessionFactory sessionFactory;

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
        sessionFactory.getCurrentSession().clear();

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
        sessionFactory.getCurrentSession().clear();

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
        final Faculty faculty = new Faculty(null, "faculty", 10);
        Faculty facultyToSave = new DefaultFacultyDao(sessionFactory).saveFaculty(faculty);
        assertNotNull(facultyToSave);

        final Exam exam = new Exam(null, facultyToSave.getId(), "question", "answer");
        Exam examToSave = examDao.saveExam(exam);
        assertNotNull(examToSave);

        assertEquals(facultyToSave.getId(), examToSave.getFacultyId());
    }

    @Test
    void getList(){
        List<Exam> faculties = new ArrayList<>();
        faculties.add(new Exam(null, 1L, "question1", "answer1"));
        faculties.add(new Exam(null, 2L, "question2", "answer2"));

        List<Exam> examList = new ArrayList<>();
        for (Exam e: faculties) {
            examList.add(examDao.saveExam(e));
        }
        assertNotNull(examList);
        for (int i = 0; i < examList.size(); i++) {
            assertEquals(examList.get(i).getFacultyId(), faculties.get(i).getFacultyId());
            assertEquals(examList.get(i).getQuestion(), faculties.get(i).getQuestion());
            assertEquals(examList.get(i).getAnswer(), faculties.get(i).getAnswer());
        }
        faculties = examDao.getExams();
        assertNotNull(faculties);
    }

    @Test
    void getListFaculty(){
        List<Exam> faculties = new ArrayList<>();
        faculties.add(new Exam(null, 1L, "question1", "answer1"));
        faculties.add(new Exam(null, 2L, "question2", "answer2"));

        List<Exam> examList = new ArrayList<>();
        for (Exam e: faculties) {
            examList.add(examDao.saveExam(e));
        }
        assertNotNull(examList);
        for (int i = 0; i < examList.size(); i++) {
            assertEquals(examList.get(i).getFacultyId(), faculties.get(i).getFacultyId());
            assertEquals(examList.get(i).getQuestion(), faculties.get(i).getQuestion());
            assertEquals(examList.get(i).getAnswer(), faculties.get(i).getAnswer());
        }
        faculties = examDao.getExams(1L);
        assertNotNull(faculties);
    }

    @Test
    void getListNumber(){
        List<Exam> faculties = new ArrayList<>();
        faculties.add(new Exam(null, 1L, "question1", "answer1"));
        faculties.add(new Exam(null, 2L, "question2", "answer2"));

        List<Exam> examList = new ArrayList<>();
        for (Exam e: faculties) {
            examList.add(examDao.saveExam(e));
        }
        assertNotNull(examList);
        for (int i = 0; i < examList.size(); i++) {
            assertEquals(examList.get(i).getFacultyId(), faculties.get(i).getFacultyId());
            assertEquals(examList.get(i).getQuestion(), faculties.get(i).getQuestion());
            assertEquals(examList.get(i).getAnswer(), faculties.get(i).getAnswer());
        }
        faculties = examDao.getExams(1);
        assertNotNull(faculties);
    }
}