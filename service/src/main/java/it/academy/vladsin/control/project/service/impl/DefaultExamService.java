package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.ExamDao;
import it.academy.vladsin.control.project.dao.impl.DefaultExamDao;
import it.academy.vladsin.control.project.data.Exam;
import it.academy.vladsin.control.project.service.ExamService;

import java.util.List;

public class DefaultExamService implements ExamService {

    private ExamDao examDao = DefaultExamDao.getInstance();

    private static class SingletonHolder {
        static final ExamService HOLDER_INSTANCE = new DefaultExamService();
    }
    public static ExamService getInstance() {
        return DefaultExamService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Exam saveExam(Exam exam) {
        return examDao.saveExam(exam);
    }

    @Override
    public boolean deleteExam(long id) {
        return examDao.deleteExam(id);
    }

    @Override
    public boolean updateExam(Exam exam) {
        return examDao.updateExam(exam);
    }

    @Override
    public Exam getExam(long id) {
        return examDao.getExam(id);
    }

    @Override
    public List<Exam> getExams() {
        return examDao.getExams();
    }

    @Override
    public List<Exam> getExams(Long facultyId) {
        return examDao.getExams(facultyId);
    }

    @Override
    public List<Exam> getExams(int number) {
        return examDao.getExams(number);
    }
}
