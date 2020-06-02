package it.academy.control.project.dao.impl;

import it.academy.control.project.dao.ApplicantDao;
import it.academy.control.project.dao.converter.ApplicantConverter;
import it.academy.control.project.dao.converter.UserConverter;
import it.academy.control.project.dao.entity.ApplicantEntity;
import it.academy.control.project.dao.entity.UserEntity;
import it.academy.control.project.dao.util.HibernateUtil;
import it.academy.control.project.data.Applicant;

import org.hibernate.Session;
import org.hibernate.query.Query;
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
        try {
            UserEntity userEntity = UserConverter.toEntity(DefaultUserDao.getInstance().getUser(applicant.getUserId()));
            applicantEntity.setUserEntity(userEntity);
        } catch (NullPointerException e){

        }
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(applicantEntity);
        session.getTransaction().commit();
        log.info("applicant saved:{}", applicant);
        return ApplicantConverter.fromEntity(applicantEntity);
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
        ApplicantEntity applicantEntity = session.load(ApplicantEntity.class, id);
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
        Query<ApplicantEntity> query = HibernateUtil.getSession().createQuery("from ApplicantEntity ")
                .setCacheable(true);
        return query.stream()
                .map(ApplicantConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Applicant> getApplicants(int number) {
        final List<ApplicantEntity> applicantEntities = HibernateUtil.getSession().createQuery("from ApplicantEntity ")
                .setMaxResults(number)
                .setFirstResult(0)
                .list();
        return applicantEntities.stream()
                .map(ApplicantConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
