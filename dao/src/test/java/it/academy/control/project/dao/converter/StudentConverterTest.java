package it.academy.control.project.dao.converter;

import it.academy.control.project.dao.entity.StudentEntity;
import it.academy.control.project.data.Student;
import it.academy.control.project.data.University;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentConverterTest {

    @Test
    void fromEntity(){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentId(null);
        studentEntity.setUserId(1L);

        Student student = StudentConverter.fromEntity(studentEntity);
        assertNotNull(student);
        assertEquals(student.getUserId(), studentEntity.getUserId());
        assertEquals(student.getId(), studentEntity.getStudentId());
    }

    @Test
    void toEntity(){
        Student student = new Student(null, 1L, null);
        StudentEntity studentEntity = StudentConverter.toEntity(student);
        assertNotNull(studentEntity);
        assertEquals(student.getUserId(), studentEntity.getUserId());
        assertEquals(student.getId(), studentEntity.getStudentId());
    }
}
