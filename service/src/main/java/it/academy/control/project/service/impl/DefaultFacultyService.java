package it.academy.control.project.service.impl;

import it.academy.control.project.dao.FacultyDao;
import it.academy.control.project.dao.impl.DefaultFacultyDao;
import it.academy.control.project.data.Faculty;
import it.academy.control.project.service.FacultyService;

import java.util.List;

public class DefaultFacultyService implements FacultyService {

    private FacultyDao facultyDao = DefaultFacultyDao.getInstance();

    private static class SingletonHolder {
        static final FacultyService HOLDER_INSTANCE = new DefaultFacultyService();
    }
    public static FacultyService getInstance() {
        return DefaultFacultyService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Faculty saveFaculty(Faculty faculty) {
        return facultyDao.saveFaculty(faculty);
    }

    @Override
    public boolean deleteFaculty(long id) {
        return facultyDao.deleteFaculty(id);
    }

    @Override
    public boolean updateFaculty(Faculty faculty) {
        return facultyDao.updateFaculty(faculty);
    }

    @Override
    public Faculty getFaculty(long id) {
        return facultyDao.getFaculty(id);
    }

    @Override
    public List<Faculty> getFaculty() {
        return facultyDao.getFaculty();
    }
}
