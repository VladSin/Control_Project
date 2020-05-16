package it.academy.control.project.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "faculty")
public class FacultyEntity {

    private Long id;
    private String faculty;
    private int mark;

    public FacultyEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "faculty")
    public String getFaculty() {
        return faculty;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Column(name = "mark")
    public int getMark() {
        return mark;
    }
    public void setMark(int mark) {
        this.mark = mark;
    }
}
