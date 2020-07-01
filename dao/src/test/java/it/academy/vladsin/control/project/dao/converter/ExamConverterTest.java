package it.academy.vladsin.control.project.dao.converter;

import it.academy.vladsin.control.project.dao.entity.ExamEntity;
import it.academy.vladsin.control.project.data.Exam;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExamConverterTest {

    @Test
    void fromEntity(){
        ExamEntity examEntity = new ExamEntity();
        examEntity.setId(null);
        examEntity.setFacultyId(1L);
        examEntity.setQuestion("question");
        examEntity.setAnswer("answer");

        Exam exam = ExamConverter.fromEntity(examEntity);
        assertNotNull(exam);
        assertEquals(exam.getFacultyId(), examEntity.getFacultyId());
        assertEquals(exam.getQuestion(), examEntity.getQuestion());
        assertEquals(exam.getAnswer(), examEntity.getAnswer());
    }

    @Test
    void toEntity(){
        Exam exam = new Exam(null, 1L, "question", "answer");
        ExamEntity examEntity = ExamConverter.toEntity(exam);
        assertNotNull(exam);
        assertEquals(exam.getFacultyId(), examEntity.getFacultyId());
        assertEquals(exam.getQuestion(), examEntity.getQuestion());
        assertEquals(exam.getAnswer(), examEntity.getAnswer());
    }
}
