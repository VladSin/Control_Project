package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.UniversityDao;
import it.academy.vladsin.control.project.dao.converter.UniversityConverter;
import it.academy.vladsin.control.project.dao.entity.UniversityEntity;
import it.academy.vladsin.control.project.data.University;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultUniversityDao implements UniversityDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultUniversityDao.class);

    private final SessionFactory factory;

    public DefaultUniversityDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public University saveUniversity(University university) {
        UniversityEntity universityEntity = UniversityConverter.toEntity(university);
        final Session session = factory.getCurrentSession();
        session.save(universityEntity);
        log.info("university saved:{}", university);
        return UniversityConverter.fromEntity(universityEntity);
    }

    @Override
    public boolean deleteUniversity(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from UniversityEntity as a where a.universityId = :id")
                .setParameter("id", id)
                .executeUpdate();
        return true;
    }

    @Override
    public boolean updateUniversity(University university) {
        UniversityEntity universityEntity = UniversityConverter.toEntity(university);
        final Session session = factory.getCurrentSession();
        session.update(universityEntity);
        return true;
    }

    @Override
    public University getUniversity(long id) {
        final Session session = factory.getCurrentSession();
        UniversityEntity universityEntity = session.get(UniversityEntity.class, id);
        try {
            University university = UniversityConverter.fromEntity(universityEntity);
            return university;
        } catch (RuntimeException e){
            log.info("university not found by id{}", id);
            return null;
        }
    }

    @Override
    public List<University> getUniversities() {
        final List<UniversityEntity> universityEntities = factory.getCurrentSession().createQuery("from UniversityEntity")
                .list();
        return universityEntities.stream()
                .map(UniversityConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
