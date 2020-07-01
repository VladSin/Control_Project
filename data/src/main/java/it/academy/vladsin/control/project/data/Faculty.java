package it.academy.vladsin.control.project.data;

public class Faculty {

    private Long id;
    private String faculty;
    private int mark;

    public Faculty(Long id, String faculty, int mark) {
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

    public int getMark() {
        return mark;
    }
    public void setMark(int mark) {
        this.mark = mark;
    }
}
