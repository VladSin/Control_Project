package it_academy.control_project.dao;

import it_academy.control_project.data.Faculty;

import java.util.List;

public interface IFacultyStorage {

    Faculty saveFaculty(Faculty faculty);
    List<Faculty> saveFaculty(List<Faculty> salaries);

    boolean deleteFaculty(long id);
    boolean updateFaculty(Faculty faculty);

    Faculty getFaculty(long id);
    List<Faculty> getFaculty();
}
