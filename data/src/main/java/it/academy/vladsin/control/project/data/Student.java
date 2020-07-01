package it.academy.vladsin.control.project.data;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private Long id;
    private Long userId;
    private List<University> universities;

    public Student(Long id, Long userId, List<University> universities) {
        this.id = id;
        this.userId = userId;
        this.universities = universities;
    }
    public Student(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<University> getUniversities() {
        return universities;
    }
    public void setUniversities(University university) {
        this.universities = universities;
    }

    public void addUniversities(University university){
        if(this.universities == null){
            List<University> universities = new ArrayList<>();
            universities.add(university);
            this.universities = universities;
        } else {
            this.universities.add(university);
        }
    }
}
