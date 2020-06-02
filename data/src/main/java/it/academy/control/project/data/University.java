package it.academy.control.project.data;

import java.util.ArrayList;
import java.util.List;

public class University {
    private Long id;
    private String university;
    private List<Student> students;

    public University(Long id, String university, List<Student> students) {
        this.id = id;
        this.university = university;
        this.students = students;
    }
    public University(Long id, String university) {
        this(id, university, new ArrayList<>());
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }

    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudents(Student student) {
        if(this.students == null){
            List<Student> students = new ArrayList<>();
            students.add(student);
            this.students = students;
        } else {
            this.students.add(student);
        }
    }
}
