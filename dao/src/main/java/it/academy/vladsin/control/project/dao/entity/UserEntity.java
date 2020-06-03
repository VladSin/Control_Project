package it.academy.vladsin.control.project.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "userEntity", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private ApplicantEntity applicantEntity;


    public UserEntity(){
    }
    public UserEntity(Long id, String name, String surname, String phone, String email, ApplicantEntity applicantEntity){
        this.applicantEntity = applicantEntity;
        this.email = email;
        this.phone = phone;
        this.surname = surname;
        this.name = name;
        this.id = id;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public ApplicantEntity getApplicantEntity() {
        return applicantEntity;
    }
    public void setApplicantEntity(ApplicantEntity applicantEntity) {
        this.applicantEntity = applicantEntity;
    }
}
