package it.academy.vladsin.control.project.dao.converter;

import it.academy.vladsin.control.project.dao.entity.StudentEntity;
import it.academy.vladsin.control.project.data.Student;

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
