package it.academy.control.project.service;

import it.academy.control.project.data.University;

import java.util.List;

public interface UniversityService {

    University saveUniversity(University university);

    boolean deleteUniversity(long id);

    boolean updateUniversity(University university);

    University getUniversity(long id);

    List<University> getUniversities();
}
