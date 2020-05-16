package it.academy.control.project.dao;

import it.academy.control.project.data.User;

import java.util.List;

public interface UserDao {

    User saveUser(User user);

    boolean deleteUser(long id);

    boolean updateUser(User user);

    User getUser(long id);

    List<User> getUser();
}
