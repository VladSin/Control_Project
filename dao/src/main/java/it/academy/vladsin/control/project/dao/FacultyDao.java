package it.academy.vladsin.control.project.dao;

import it.academy.vladsin.control.project.data.Exam;
import it.academy.vladsin.control.project.data.Faculty;

import java.util.List;

public interface FacultyDao {

    Faculty saveFaculty(Faculty faculty);

    boolean deleteFaculty(long id);

    boolean updateFaculty(Faculty faculty);

    Faculty getFaculty(long id);

    List<Faculty> getFaculties();

    List<Exam> getExamForFaculty();
}
