package it.academy.control.project.dao.entity;

import it.academy.control.project.dao.converter.StudentConverter;
import it.academy.control.project.dao.converter.UniversityConverter;
import it.academy.control.project.data.University;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "student")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", unique = true)
    private Long studentId;

    @Column(name = "user_id")
    private Long userId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_university",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "university_id")})
    private List<UniversityEntity> universities = new ArrayList<>();

    public StudentEntity(){}
    public StudentEntity(Long studentId, Long userId) {
        this.studentId = studentId;
        this.userId = userId;
        this.universities = universities;
    }

    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<UniversityEntity> getUniversities() {
        return universities;
    }
    public void setUniversities(List<UniversityEntity> universities) {
        this.universities = universities;
    }

    public void setUniversitiesData(List<University> universities) {
        this.universities = universities.stream()
                .map(UniversityConverter::toEntity)
                .collect(Collectors.toList());
    }
}
