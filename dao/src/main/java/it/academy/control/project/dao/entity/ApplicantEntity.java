package it.academy.control.project.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "applicant")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApplicantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", updatable = false, insertable = false)
    private Long userId;

    @Column(name = "faculty_id")
    private Long facultyId;

    @Column(name = "mark")
    private int mark;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    public ApplicantEntity(){
    }
    public ApplicantEntity(Long id, Long userId, Long facultyId, int mark, UserEntity userEntity){
        this.userEntity = userEntity;
        this.mark = mark;
        this.facultyId = facultyId;
        this.userId = userId;
        this.id = id;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
