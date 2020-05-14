package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.ApplicantDao;
import it_academy.control_project.dao.converter.ApplicantConverter;
import it_academy.control_project.dao.entity.ApplicantEntity;
import it_academy.control_project.dao.util.HibernateUtil;
import it_academy.control_project.data.Applicant;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultApplicantDao implements ApplicantDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultApplicantDao.class);

    private static class SingletonHolder{
        static final ApplicantDao HOLDER_INSTANCE = new DefaultApplicantDao();
    }
    public static ApplicantDao getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Applicant saveApplicant(Applicant applicant) {
        ApplicantEntity applicantEntity = ApplicantConverter.toEntity(applicant);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(applicantEntity);
        session.getTransaction().commit();
        log.info("applicant saved:{}", applicant);
        return applicant;
    }

    @Override
    public boolean deleteApplicant(long id) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete from ApplicantEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateApplicant(Applicant applicant) {
        ApplicantEntity applicantEntity = ApplicantConverter.toEntity(applicant);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(applicantEntity);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Applicant getApplicant(long id) {
        final Session session = HibernateUtil.getSession();
        ApplicantEntity authUserEntity = session.load(ApplicantEntity.class, id);
        return ApplicantConverter.fromEntity(authUserEntity);
    }

    @Override
    public List<Applicant> getApplicant() {
        final List<ApplicantEntity> applicantEntities = HibernateUtil.getSession().createQuery("from ApplicantEntity ")
                .list();
        return applicantEntities.stream()
                .map(ApplicantConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
