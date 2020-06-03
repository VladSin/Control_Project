package it.academy.vladsin.control.project.dao.entity;


import it.academy.vladsin.control.project.dao.converter.StudentConverter;
import it.academy.vladsin.control.project.data.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "university")
public class UniversityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_id")
    private Long universityId;

    @Column(name = "university")
    private String university;

    @ManyToMany(mappedBy = "universities", cascade = CascadeType.ALL)
    private List<StudentEntity> students = new ArrayList<>();

    public UniversityEntity(){}
    public UniversityEntity(Long universityId, String university) {
        this.universityId = universityId;
        this.university = university;
        this.students = students;
    }

    public Long getUniversityId() {
        return universityId;
    }
    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }
    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

    public void setStudentsData(List<Student> students) {
        this.students = students.stream()
                .map(StudentConverter::toEntity)
                .collect(Collectors.toList());
    }
}

