package it_academy.control_project.dao;

import it_academy.control_project.data.User;

import java.util.List;

public interface UserDao {

    User saveUser(User user);

    boolean deleteUser(long id);

    boolean updateUser(User user);

    User getUser(long id);

    List<User> getUser();
}
