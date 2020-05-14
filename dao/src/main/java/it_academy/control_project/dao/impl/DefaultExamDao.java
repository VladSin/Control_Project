package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.ExamDao;
import it_academy.control_project.dao.converter.ExamConverter;
import it_academy.control_project.dao.entity.ExamEntity;
import it_academy.control_project.dao.util.HibernateUtil;
import it_academy.control_project.data.Exam;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultExamDao implements ExamDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultExamDao.class);

    private static class SingletonHolder{
        static final ExamDao HOLDER_INSTANCE = new DefaultExamDao();
    }
    public static ExamDao getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Exam saveExam(Exam exam) {
        ExamEntity examEntity = ExamConverter.toEntity(exam);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(examEntity);
        session.getTransaction().commit();
        log.info("authUser saved:{}", exam);
        return exam;
    }

    @Override
    public boolean deleteExam(long id) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete from ExamEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateExam(Exam exam) {
        ExamEntity examEntity = ExamConverter.toEntity(exam);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(examEntity);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Exam getExam(long id) {
        final Session session = HibernateUtil.getSession();
        ExamEntity examEntity = session.load(ExamEntity.class, id);
        return ExamConverter.fromEntity(examEntity);
    }

    @Override
    public List<Exam> getExam() {
        final List<ExamEntity> examEntities = HibernateUtil.getSession().createQuery("from ExamEntity ")
                .list();
        return examEntities.stream()
                .map(ExamConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
