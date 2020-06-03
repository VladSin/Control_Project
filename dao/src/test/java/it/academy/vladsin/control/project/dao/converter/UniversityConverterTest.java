package it.academy.vladsin.control.project.dao.converter;

import it.academy.vladsin.control.project.dao.entity.UniversityEntity;
import it.academy.vladsin.control.project.data.University;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UniversityConverterTest {

    @Test
    void fromEntity(){
        UniversityEntity universities = new UniversityEntity();
        universities.setUniversityId(null);
        universities.setUniversity("university");

        University university = UniversityConverter.fromEntity(universities);
        assertNotNull(university);
        assertEquals(university.getUniversity(), universities.getUniversity());
        assertEquals(university.getId(), universities.getUniversityId());
    }

    @Test
    void toEntity(){
        University university = new University(null, "university", null);
        UniversityEntity universityEntity = UniversityConverter.toEntity(university);
        assertNotNull(universityEntity);
        assertEquals(university.getUniversity(), universityEntity.getUniversity());
        assertEquals(university.getId(), universityEntity.getUniversityId());
    }
}
