package it_academy.control_project.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "auth_user")
public class AuthorizationUserEntity {

    private Long id;
    private String login;
    private String password;
    private String role;

    public AuthorizationUserEntity(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "role")
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
