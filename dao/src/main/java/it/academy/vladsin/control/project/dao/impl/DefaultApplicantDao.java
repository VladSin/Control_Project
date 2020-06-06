package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.ApplicantDao;
import it.academy.vladsin.control.project.dao.converter.ApplicantConverter;
import it.academy.vladsin.control.project.dao.converter.UserConverter;
import it.academy.vladsin.control.project.dao.entity.ApplicantEntity;
import it.academy.vladsin.control.project.dao.entity.UserEntity;
import it.academy.vladsin.control.project.data.Applicant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultApplicantDao implements ApplicantDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultApplicantDao.class);

    private final SessionFactory factory;

    public DefaultApplicantDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Applicant saveApplicant(Applicant applicant) {
        ApplicantEntity applicantEntity = ApplicantConverter.toEntity(applicant);
        try {
            UserEntity userEntity = UserConverter.toEntity(new DefaultUserDao(factory).getUser(applicant.getUserId()));
            applicantEntity.setUserEntity(userEntity);
        } catch (NullPointerException e){
            log.info("user is not found for:{}", applicant);
            throw new RuntimeException(e);
        }
        final Session session = factory.getCurrentSession();
        session.save(applicantEntity);
        log.info("applicant saved:{}", applicant);
        return ApplicantConverter.fromEntity(applicantEntity);
    }

    @Override
    public boolean deleteApplicant(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from ApplicantEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.flush();
        return true;
    }

    @Override
    public boolean updateApplicant(Applicant applicant) {
        ApplicantEntity applicantEntity = ApplicantConverter.toEntity(applicant);
        final Session session = factory.getCurrentSession();
        session.update(applicantEntity);
        return true;
    }

    @Override
    public Applicant getApplicant(long id) {
        final Session session = factory.getCurrentSession();
        ApplicantEntity applicantEntity = session.get(ApplicantEntity.class, id);
        try{
            Applicant applicant = ApplicantConverter.fromEntity(applicantEntity);
            return applicant;
        } catch (RuntimeException e){
            log.info("applicant not found by id{}", id);
            return null;
        }
    }

    @Override
    public List<Applicant> getApplicants() {
        Query<ApplicantEntity> query = factory.getCurrentSession().createQuery("from ApplicantEntity ")
                .setCacheable(true);
        return query.stream()
                .map(ApplicantConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Applicant> getApplicants(int number) {
        final List<ApplicantEntity> applicantEntities = factory.getCurrentSession().createQuery("from ApplicantEntity ")
                .setMaxResults(number)
                .setFirstResult(0)
                .list();
        return applicantEntities.stream()
                .map(ApplicantConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
