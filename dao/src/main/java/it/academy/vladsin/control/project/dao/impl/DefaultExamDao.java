package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.ExamDao;
import it.academy.vladsin.control.project.dao.converter.ExamConverter;
import it.academy.vladsin.control.project.dao.entity.ExamEntity;
import it.academy.vladsin.control.project.data.Exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultExamDao implements ExamDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultExamDao.class);

    private final SessionFactory factory;

    public DefaultExamDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Exam saveExam(Exam exam) {
        ExamEntity examEntity = ExamConverter.toEntity(exam);
        final Session session = factory.getCurrentSession();
        session.save(examEntity);
        log.info("exam saved:{}", exam);
        return ExamConverter.fromEntity(examEntity);
    }

    @Override
    public boolean deleteExam(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from ExamEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return true;
    }

    @Override
    public boolean updateExam(Exam exam) {
        ExamEntity examEntity = ExamConverter.toEntity(exam);
        final Session session = factory.getCurrentSession();
        session.update(examEntity);
        return true;
    }

    @Override
    public Exam getExam(long id) {
        final Session session = factory.getCurrentSession();
        ExamEntity examEntity = session.load(ExamEntity.class, id);
        try {
            Exam exam = ExamConverter.fromEntity(examEntity);
            return exam;
        } catch (RuntimeException e){
            log.info("exam not found by id{}", id);
            return null;
        }
    }

    @Override
    public List<Exam> getExams() {
        Query<ExamEntity> query = factory.getCurrentSession().createQuery("from ExamEntity ")
                .setCacheable(true);
        return query.stream()
                .map(ExamConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Exam> getExams(Long facultyId) {
        final Session session = factory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ExamEntity> criteria = cb.createQuery(ExamEntity.class);
        Root<ExamEntity> entityRoot = criteria.from(ExamEntity.class);
        Predicate predicate = cb.and(
                cb.equal(entityRoot.get("facultyId"),  facultyId)
        );
        criteria.select(entityRoot).where(predicate);

        List<ExamEntity> examEntities = session.createQuery(criteria).getResultList();
        return examEntities.stream()
                .map(ExamConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Exam> getExams(int number) {
        final List<ExamEntity> examEntities = factory.getCurrentSession().createQuery("from ExamEntity ")
                .setMaxResults(number)
                .setFirstResult(0)
                .getResultList();
        return examEntities.stream()
                .map(ExamConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
