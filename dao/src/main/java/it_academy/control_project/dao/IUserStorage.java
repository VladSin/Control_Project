package it_academy.control_project.dao;

import it_academy.control_project.data.User;

import java.util.List;

public interface IUserStorage {
    User saveUser(User user);
    List<User> saveUser(List<User> users);

    boolean deleteUser(long id);
    boolean updateUser(User user);

    User getUser(long id);
    List<User> getUser();
}