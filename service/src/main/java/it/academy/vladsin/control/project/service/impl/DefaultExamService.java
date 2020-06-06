package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.ExamDao;
import it.academy.vladsin.control.project.data.Exam;
import it.academy.vladsin.control.project.service.ExamService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultExamService implements ExamService {

    private final ExamDao examDao;

    public DefaultExamService(ExamDao examDao) {
        this.examDao = examDao;
    }

    @Override
    @Transactional
    public Exam saveExam(Exam exam) {
        return examDao.saveExam(exam);
    }

    @Override
    @Transactional
    public boolean deleteExam(long id) {
        return examDao.deleteExam(id);
    }

    @Override
    @Transactional
    public boolean updateExam(Exam exam) {
        return examDao.updateExam(exam);
    }

    @Override
    @Transactional
    public Exam getExam(long id) {
        return examDao.getExam(id);
    }

    @Override
    @Transactional
    public List<Exam> getExams() {
        return examDao.getExams();
    }

    @Override
    @Transactional
    public List<Exam> getExams(Long facultyId) {
        return examDao.getExams(facultyId);
    }

    @Override
    @Transactional
    public List<Exam> getExams(int number) {
        return examDao.getExams(number);
    }
}
