package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.StudentDao;
import it.academy.vladsin.control.project.dao.converter.StudentConverter;
import it.academy.vladsin.control.project.dao.entity.StudentEntity;
import it.academy.vladsin.control.project.data.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultStudentDao implements StudentDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultStudentDao.class);

    private final SessionFactory factory;

    public DefaultStudentDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Student saveStudent(Student student) {
        StudentEntity studentEntity = StudentConverter.toEntity(student);
        final Session session = factory.getCurrentSession();
        session.save(studentEntity);
        log.info("student saved:{}", student);
        return StudentConverter.fromEntity(studentEntity);
    }

    @Override
    public boolean deleteStudent(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from StudentEntity as a where a.studentId = :id")
                .setParameter("id", id)
                .executeUpdate();
        return true;
    }

    @Override
    public boolean updateStudent(Student student) {
        StudentEntity studentEntity = StudentConverter.toEntity(student);
        final Session session = factory.getCurrentSession();
        session.update(studentEntity);
        return true;
    }

    @Override
    public Student getStudent(long id) {
        final Session session = factory.getCurrentSession();
        StudentEntity studentEntity = session.get(StudentEntity.class, id);
        try{
            Student student = StudentConverter.fromEntity(studentEntity);
            return student;
        } catch (RuntimeException e){
            log.info("student not found by id{}", id);
            return null;
        }
    }

    @Override
    public List<Student> getStudents() {
        Query<StudentEntity> query = factory.getCurrentSession().createQuery("from StudentEntity")
                .setCacheable(true);
        return query.stream()
                .map(StudentConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudents(int number) {
        final List<StudentEntity> studentEntities = factory.getCurrentSession().createQuery("from StudentEntity")
                .setMaxResults(number)
                .setFirstResult(0)
                .list();
        return studentEntities.stream()
                .map(StudentConverter::fromEntity)
                .collect(Collectors.toList());
    }

}
