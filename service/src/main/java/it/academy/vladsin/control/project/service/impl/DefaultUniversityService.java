package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.UniversityDao;
import it.academy.vladsin.control.project.data.University;
import it.academy.vladsin.control.project.service.UniversityService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultUniversityService implements UniversityService {

    private final UniversityDao universityDao;

    public DefaultUniversityService(UniversityDao universityDao) {
        this.universityDao = universityDao;
    }

    @Override
    @Transactional
    public University saveUniversity(University university) {
        return universityDao.saveUniversity(university);
    }

    @Override
    @Transactional
    public boolean deleteUniversity(long id) {
        return universityDao.deleteUniversity(id);
    }

    @Override
    @Transactional
    public boolean updateUniversity(University university) {
        return universityDao.updateUniversity(university);
    }

    @Override
    @Transactional
    public University getUniversity(long id) {
        return universityDao.getUniversity(id);
    }

    @Override
    @Transactional
    public List<University> getUniversities() {
        return universityDao.getUniversities();
    }
}
