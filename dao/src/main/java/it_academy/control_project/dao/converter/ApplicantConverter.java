package it_academy.control_project.dao.converter;

import it_academy.control_project.dao.entity.ApplicantEntity;
import it_academy.control_project.data.Applicant;

public class ApplicantConverter {

    public static Applicant fromEntity(ApplicantEntity applicantEntity){
        if(applicantEntity == null){
            return null;
        }
        return new Applicant(
                applicantEntity.getId(),
                applicantEntity.getUserId(),
                applicantEntity.getFacultyId(),
                applicantEntity.getMark()
        );
    }

    public static ApplicantEntity toEntity(Applicant applicant){
        if(applicant == null){
            return null;
        }
        final ApplicantEntity applicantEntity = new ApplicantEntity();
        applicantEntity.setId(applicant.getId());
        applicantEntity.setUserId(applicant.getUserId());
        applicantEntity.setFacultyId(applicant.getFacultyId());
        applicantEntity.setMark(applicant.getMark());
        return applicantEntity;
    }
}
