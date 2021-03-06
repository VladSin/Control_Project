package it.academy.vladsin.control.project.dao.converter;

import it.academy.vladsin.control.project.dao.entity.ExamEntity;
import it.academy.vladsin.control.project.data.Exam;

public class ExamConverter {

    public static Exam fromEntity(ExamEntity examEntity){
        if(examEntity == null){
            return null;
        }
        return new Exam(
                examEntity.getId(),
                examEntity.getFacultyId(),
                examEntity.getQuestion(),
                examEntity.getAnswer()
        );
    }

    public static ExamEntity toEntity(Exam exam){
        if(exam == null){
            return null;
        }
        final ExamEntity examEntity = new ExamEntity();
        examEntity.setId(exam.getId());
        examEntity.setFacultyId(exam.getFacultyId());
        examEntity.setQuestion(exam.getQuestion());
        examEntity.setAnswer(exam.getAnswer());
        return examEntity;
    }
}
