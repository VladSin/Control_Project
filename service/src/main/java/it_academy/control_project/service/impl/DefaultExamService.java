package it_academy.control_project.service.impl;

import it_academy.control_project.dao.impl.DefaultExamStorage;
import it_academy.control_project.data.Exam;
import it_academy.control_project.service.IExamService;

import java.util.List;

public class DefaultExamService implements IExamService {

    private static class SingletonHolder {
        static final IExamService HOLDER_INSTANCE = new DefaultExamService();
    }
    public static IExamService getInstance() {
        return DefaultExamService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Exam saveExam(Exam exam) {
        return DefaultExamStorage.getInstance().saveExam(exam);
    }

    @Override
    public List<Exam> saveExam(List<Exam> exams) {
        return DefaultExamStorage.getInstance().saveExam(exams);
    }

    @Override
    public boolean deleteExam(long id) {
        return DefaultExamStorage.getInstance().deleteExam(id);
    }

    @Override
    public boolean updateExam(Exam exam) {
        return DefaultExamStorage.getInstance().updateExam(exam);
    }

    @Override
    public Exam getExam(long id) {
        return DefaultExamStorage.getInstance().getExam(id);
    }

    @Override
    public List<Exam> getExam() {
        return DefaultExamStorage.getInstance().getExam();
    }
}
