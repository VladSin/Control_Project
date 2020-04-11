package it_academy.control_project.dao;

import it_academy.control_project.data.User;

import java.util.List;

public interface IUserStorage {
    List<User> getUsers();
    Long save(User user);
}
