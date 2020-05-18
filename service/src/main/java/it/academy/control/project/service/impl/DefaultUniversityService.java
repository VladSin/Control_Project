package it.academy.control.project.service.impl;

import it.academy.control.project.dao.UniversityDao;
import it.academy.control.project.dao.impl.DefaultUniversityDao;
import it.academy.control.project.data.University;
import it.academy.control.project.service.UniversityService;

import java.util.List;

public class DefaultUniversityService implements UniversityService {

    private UniversityDao universityDao = DefaultUniversityDao.getInstance();

    private static class SingletonHolder{
        static final UniversityService HOLDER_INSTANCE = new DefaultUniversityService();
    }
    public static UniversityService getInstance(){
        return DefaultUniversityService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public University saveUniversity(University university) {
        return universityDao.saveUniversity(university);
    }

    @Override
    public boolean deleteUniversity(long id) {
        return universityDao.deleteUniversity(id);
    }

    @Override
    public boolean updateUniversity(University university) {
        return universityDao.updateUniversity(university);
    }

    @Override
    public University getUniversity(long id) {
        return universityDao.getUniversity(id);
    }

    @Override
    public List<University> getUniversities() {
        return universityDao.getUniversities();
    }
}
