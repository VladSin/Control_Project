package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.FacultyDao;
import it.academy.vladsin.control.project.data.Exam;
import it.academy.vladsin.control.project.data.Faculty;
import it.academy.vladsin.control.project.service.FacultyService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultFacultyService implements FacultyService {

    private final FacultyDao facultyDao;

    public DefaultFacultyService(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    @Override
    @Transactional
    public Faculty saveFaculty(Faculty faculty) {
        return facultyDao.saveFaculty(faculty);
    }

    @Override
    @Transactional
    public boolean deleteFaculty(long id) {
        return facultyDao.deleteFaculty(id);
    }

    @Override
    @Transactional
    public boolean updateFaculty(Faculty faculty) {
        return facultyDao.updateFaculty(faculty);
    }

    @Override
    @Transactional
    public Faculty getFaculty(long id) {
        return facultyDao.getFaculty(id);
    }

    @Override
    @Transactional
    public List<Faculty> getFaculties() {
        return facultyDao.getFaculties();
    }

    @Override
    @Transactional
    public List<Exam> getExamForFaculty() {
        return facultyDao.getExamForFaculty();
    }
}
