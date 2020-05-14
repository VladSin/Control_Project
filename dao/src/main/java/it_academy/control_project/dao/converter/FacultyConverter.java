package it_academy.control_project.dao.converter;

import it_academy.control_project.dao.entity.FacultyEntity;
import it_academy.control_project.data.Faculty;

public class FacultyConverter {

    public static Faculty fromEntity(FacultyEntity facultyEntity){
        if(facultyEntity == null){
            return null;
        }
        return new Faculty(
                facultyEntity.getId(),
                facultyEntity.getFaculty(),
                facultyEntity.getMark()
        );
    }

    public static FacultyEntity toEntity(Faculty faculty){
        if(faculty == null){
            return null;
        }
        final FacultyEntity facultyEntity = new FacultyEntity();
        facultyEntity.setId(faculty.getId());
        facultyEntity.setFaculty(faculty.getFaculty());
        facultyEntity.setMark(faculty.getMark());
        return facultyEntity;
    }
}
