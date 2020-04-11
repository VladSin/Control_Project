package it_academy.control_project.service;

import it_academy.control_project.data.Faculty;

import java.util.List;

public interface IFacultyService {

    Faculty saveFaculty(Faculty faculty);
    List<Faculty> saveFaculty(List<Faculty> faculties);

    boolean deleteFaculty(long id);
    boolean updateFaculty(Faculty faculty);

    Faculty getFaculty(long id);
    List<Faculty> getFaculty();
}
