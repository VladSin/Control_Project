package it.academy.control.project.service;

import it.academy.control.project.data.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty saveFaculty(Faculty faculty);

    boolean deleteFaculty(long id);

    boolean updateFaculty(Faculty faculty);

    Faculty getFaculty(long id);

    List<Faculty> getFaculties();
}
