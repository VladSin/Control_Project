package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.FacultyDao;
import it.academy.vladsin.control.project.dao.converter.ExamConverter;
import it.academy.vladsin.control.project.dao.converter.FacultyConverter;
import it.academy.vladsin.control.project.dao.entity.FacultyEntity;
import it.academy.vladsin.control.project.dao.util.HibernateUtil;
import it.academy.vladsin.control.project.data.Exam;
import it.academy.vladsin.control.project.data.Faculty;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultFacultyDao implements FacultyDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultFacultyDao.class);

    private static class SingletonHolder{
        static final FacultyDao HOLDER_INSTANCE = new DefaultFacultyDao();
    }
    public static FacultyDao getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Faculty saveFaculty(Faculty faculty) {
        FacultyEntity facultyEntity = FacultyConverter.toEntity(faculty);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(facultyEntity);
        session.getTransaction().commit();
        log.info("faculty saved:{}", faculty);
        return FacultyConverter.fromEntity(facultyEntity);
    }

    @Override
    public boolean deleteFaculty(long id) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete from FacultyEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateFaculty(Faculty faculty) {
        FacultyEntity facultyEntity = FacultyConverter.toEntity(faculty);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(facultyEntity);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Faculty getFaculty(long id) {
        final Session session = HibernateUtil.getSession();
        FacultyEntity facultyEntity = session.load(FacultyEntity.class, id);
        try {
            Faculty faculty = FacultyConverter.fromEntity(facultyEntity);
            return faculty;
        } catch (RuntimeException e){
            log.info("faculty not found by id{}", id);
            return null;
        }
    }

    @Override
    public List<Faculty> getFaculties() {
        final List<FacultyEntity> facultyEntities = HibernateUtil.getSession().createQuery("from FacultyEntity")
                .list();
        return facultyEntities.stream()
                .map(FacultyConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Exam> getExamForFaculty(){
        FacultyEntity facultyEntity = new FacultyEntity();
        facultyEntity.setExamEntities(new DefaultExamDao().getExams().stream()
                .map(ExamConverter::toEntity)
                .collect(Collectors.toList()));

        return facultyEntity.getExamEntities().stream()
                .map(ExamConverter::fromEntity)
                .collect(Collectors.toList());
    }
}