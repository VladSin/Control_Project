package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.FacultyDao;
import it.academy.vladsin.control.project.dao.converter.ExamConverter;
import it.academy.vladsin.control.project.dao.converter.FacultyConverter;
import it.academy.vladsin.control.project.dao.entity.FacultyEntity;
import it.academy.vladsin.control.project.data.Exam;
import it.academy.vladsin.control.project.data.Faculty;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultFacultyDao implements FacultyDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultFacultyDao.class);

    private final SessionFactory factory;

    public DefaultFacultyDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Faculty saveFaculty(Faculty faculty) {
        FacultyEntity facultyEntity = FacultyConverter.toEntity(faculty);
        final Session session = factory.getCurrentSession();
        session.save(facultyEntity);
        log.info("faculty saved:{}", faculty);
        return FacultyConverter.fromEntity(facultyEntity);
    }

    @Override
    public boolean deleteFaculty(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from FacultyEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return true;
    }

    @Override
    public boolean updateFaculty(Faculty faculty) {
        FacultyEntity facultyEntity = FacultyConverter.toEntity(faculty);
        final Session session = factory.getCurrentSession();
        session.update(facultyEntity);
        return true;
    }

    @Override
    public Faculty getFaculty(long id) {
        final Session session = factory.getCurrentSession();
        FacultyEntity facultyEntity = session.get(FacultyEntity.class, id);
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
        final List<FacultyEntity> facultyEntities = factory.getCurrentSession().createQuery("from FacultyEntity")
                .list();
        return facultyEntities.stream()
                .map(FacultyConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Exam> getExamForFaculty(){
        FacultyEntity facultyEntity = new FacultyEntity();
        facultyEntity.setExamEntities(new DefaultExamDao(factory).getExams().stream()
                .map(ExamConverter::toEntity)
                .collect(Collectors.toList()));

        return facultyEntity.getExamEntities().stream()
                .map(ExamConverter::fromEntity)
                .collect(Collectors.toList());
    }
}