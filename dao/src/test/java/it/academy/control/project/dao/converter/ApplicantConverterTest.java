package it.academy.control.project.dao.converter;

import it.academy.control.project.dao.entity.ApplicantEntity;
import it.academy.control.project.data.Applicant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicantConverterTest {

    @Test
    void fromEntity(){
        ApplicantEntity applicantEntity = new ApplicantEntity();
        applicantEntity.setId(null);
        applicantEntity.setUserId(1L);
        applicantEntity.setFacultyId(1L);
        applicantEntity.setMark(10);

        Applicant applicant = ApplicantConverter.fromEntity(applicantEntity);
        assertNotNull(applicant);
        assertEquals(applicant.getUserId(), applicantEntity.getUserId());
        assertEquals(applicant.getFacultyId(), applicantEntity.getFacultyId());
        assertEquals(applicant.getMark(), applicantEntity.getMark());
    }

    @Test
    void toEntity(){
        Applicant applicant = new Applicant(null, 1L, 1L, 10);
        ApplicantEntity applicantEntity = ApplicantConverter.toEntity(applicant);
        assertNotNull(applicantEntity);
        assertEquals(applicant.getUserId(), applicantEntity.getUserId());
        assertEquals(applicant.getFacultyId(), applicantEntity.getFacultyId());
        assertEquals(applicant.getMark(), applicantEntity.getMark());
    }
}
