package it.academy.vladsin.control.project.dao.converter;

import it.academy.vladsin.control.project.dao.entity.FacultyEntity;
import it.academy.vladsin.control.project.data.Faculty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FacultyConverterTest {

    @Test
    void fromEntity(){
        FacultyEntity facultyEntity = new FacultyEntity();
        facultyEntity.setId(null);
        facultyEntity.setFaculty("faculty");
        facultyEntity.setMark(10);

        Faculty faculty = FacultyConverter.fromEntity(facultyEntity);
        assertNotNull(faculty);
        assertEquals(faculty.getFaculty(), facultyEntity.getFaculty());
        assertEquals(faculty.getMark(), facultyEntity.getMark());
    }

    @Test
    void toEntity(){
        Faculty faculty = new Faculty(null, "faculty", 10);
        FacultyEntity facultyEntity = FacultyConverter.toEntity(faculty);
        assertNotNull(facultyEntity);
        assertEquals(faculty.getFaculty(), facultyEntity.getFaculty());
        assertEquals(faculty.getMark(), facultyEntity.getMark());
    }
}
