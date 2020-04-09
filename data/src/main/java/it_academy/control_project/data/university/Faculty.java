package it_academy.control_project.data.university;

public class Faculty {

    private Long id;
    private String faculty;
    private String mark;

    public Faculty(Long id, String faculty, String mark) {
        this.id = id;
        this.faculty = faculty;
        this.mark = mark;
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

    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
}
