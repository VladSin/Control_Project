package it.academy.vladsin.control.project.dao.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "faculty")
public class FacultyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "mark")
    private int mark;

    @OneToMany(mappedBy = "facultyEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamEntity> examEntities = new ArrayList<>();

    public FacultyEntity(){}
    public FacultyEntity(Long id, String faculty, int mark, List<ExamEntity> examEntities){
        this.id = id;
        this.faculty = faculty;
        this.mark = mark;
        this.examEntities = examEntities;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getMark() {
        return mark;
    }
    public void setMark(int mark) {
        this.mark = mark;
    }

    public List<ExamEntity> getExamEntities() {
        return examEntities;
    }
    public void setExamEntities(List<ExamEntity> examEntities) {
        this.examEntities = examEntities;
    }
}
