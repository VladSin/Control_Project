package it_academy.control_project.service.impl;

import it_academy.control_project.dao.ExamDao;
import it_academy.control_project.dao.impl.DefaultExamDao;
import it_academy.control_project.data.Exam;
import it_academy.control_project.service.ExamService;

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
    public List<Exam> getExam() {
        return examDao.getExam();
    }
}
