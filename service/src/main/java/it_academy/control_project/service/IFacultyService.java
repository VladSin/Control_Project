package it_academy.control_project.service;

import it_academy.control_project.data.Faculty;

import java.util.List;

public interface IFacultyService {

    List<Faculty> getFaculty();
    Long saveFaculty(Faculty faculty);
}
