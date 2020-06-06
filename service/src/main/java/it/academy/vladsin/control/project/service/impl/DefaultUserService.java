package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.UserDao;
import it.academy.vladsin.control.project.data.User;
import it.academy.vladsin.control.project.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultUserService implements UserService {

    private final UserDao userDao;

    public DefaultUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    @Transactional
    public boolean deleteUser(long id) {
        return userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
