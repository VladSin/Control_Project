package it_academy.control_project.dao;

import it_academy.control_project.data.Faculty;

import java.util.List;

public interface IFacultyStorage {
    List<Faculty> getFaculty();
    Long save(Faculty faculty);
}
