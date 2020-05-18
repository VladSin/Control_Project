package it.academy.control.project.data;

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
    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }
}
