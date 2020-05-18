package it.academy.control.project.dao.converter;

import it.academy.control.project.dao.entity.UniversityEntity;
import it.academy.control.project.data.University;

import java.util.stream.Collectors;

public class UniversityConverter {

    public static University fromEntity(UniversityEntity universityEntity){
        if(universityEntity == null){
            return null;
        }
        return new University(
                universityEntity.getUniversityId(),
                universityEntity.getUniversity(),
                null
//                universityEntity.getStudents().stream()
//                        .map(StudentConverter::fromEntity)
//                        .collect(Collectors.toList())
        );
    }

    public static UniversityEntity toEntity(University university){
        if(university == null){
            return null;
        }
        final UniversityEntity universityEntity = new UniversityEntity();
        universityEntity.setUniversityId(university.getId());
        universityEntity.setUniversity(university.getUniversity());
//        universityEntity.setStudents(university.getStudents().stream()
//                .map(StudentConverter::toEntity)
//                .collect(Collectors.toList()));
        return universityEntity;
    }
}
