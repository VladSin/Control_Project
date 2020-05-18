package it.academy.control.project.dao;

import it.academy.control.project.data.University;

import java.util.List;

public interface UniversityDao {

    University saveUniversity(University university);

    boolean deleteUniversity(long id);

    boolean updateUniversity(University university);

    University getUniversity(long id);

    List<University> getUniversities();
}
