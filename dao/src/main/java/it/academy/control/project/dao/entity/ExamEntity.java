package it.academy.control.project.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import sun.management.snmp.util.SnmpTableCache;

import javax.persistence.*;

@Entity
@Table(name = "exam")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "faculty_id", updatable = false, insertable = false)
    private Long facultyId;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private FacultyEntity facultyEntity;

    public ExamEntity(){}
    public ExamEntity(Long id, Long facultyId, String question, String answer, FacultyEntity facultyEntity){
        this.id = id;
        this.facultyId = facultyId;
        this.question = question;
        this.answer = answer;
        this.facultyEntity = facultyEntity;
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

    public FacultyEntity getFacultyEntity() {
        return facultyEntity;
    }
    public void setFacultyEntity(FacultyEntity facultyEntity) {
        this.facultyEntity = facultyEntity;
    }
}
