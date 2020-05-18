package it.academy.control.project.dao.converter;

import it.academy.control.project.dao.entity.StudentEntity;
import it.academy.control.project.data.Student;

import java.util.stream.Collectors;

public class StudentConverter {

    public static Student fromEntity(StudentEntity studentEntity){
        if(studentEntity == null){
            return null;
        }
        return new Student(
                studentEntity.getStudentId(),
                studentEntity.getUserId(),
                null
//                studentEntity.getUniversities()
//                        .stream()
//                        .map(UniversityConverter::fromEntity)
//                        .collect(Collectors.toList())
        );
    }

    public static StudentEntity toEntity(Student student){
        if(student == null){
            return null;
        }
        final StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentId(student.getId());
        studentEntity.setUserId(student.getUserId());
//        studentEntity.setUniversities(student.getUniversities().stream()
//                .map(UniversityConverter::toEntity)
//                .collect(Collectors.toList()));
        return studentEntity;
    }
}
