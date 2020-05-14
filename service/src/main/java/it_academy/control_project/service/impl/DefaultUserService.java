package it_academy.control_project.service.impl;

import it_academy.control_project.dao.UserDao;
import it_academy.control_project.dao.impl.DefaultUserDao;
import it_academy.control_project.data.User;
import it_academy.control_project.service.UserService;

import java.util.List;

public class DefaultUserService implements UserService {

    private UserDao userDao = DefaultUserDao.getInstance();

    private static class SingletonHolder{
        static final UserService HOLDER_INSTANCE = new DefaultUserService();
    }
    public static UserService getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public boolean deleteUser(long id) {
        return userDao.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> getUser() {
        return userDao.getUser();
    }
}
