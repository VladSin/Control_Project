package it_academy.control_project.service;

import it_academy.control_project.data.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers();
    Long saveUser(User user);
}
