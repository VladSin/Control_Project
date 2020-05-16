package it.academy.control.project.data;

public class Applicant {
    private Long id;
    private Long userId;
    private Long facultyId;
    private int mark;

    public Applicant(Long id, Long userId, Long facultyId, int mark) {
        this.id = id;
        this.userId = userId;
        this.facultyId = facultyId;
        this.mark = mark;
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

    public Long getFacultyId() {
        return facultyId;
    }
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public int getMark() {
        return mark;
    }
    public void setMark(int mark) {
        this.mark = mark;
    }
}
