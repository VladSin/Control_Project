package it_academy.control_project.data;

public class Exam {

    private Long id;
    private Long facultyId;
    private String question;
    private String answer;

    public Exam(Long id, Long facultyId, String question, String answer) {
        this.id = id;
        this.facultyId = facultyId;
        this.question = question;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getFacultyId() {
        return facultyId;
    }
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
