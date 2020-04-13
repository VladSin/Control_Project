package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IUserStorage;
import it_academy.control_project.dao.impl.DefaultUserStorage;
import it_academy.control_project.data.User;
import it_academy.control_project.service.IUserService;

import java.util.List;

public class DefaultUserService implements IUserService {

    private IUserStorage userStorage = DefaultUserStorage.getInstance();

    private static class SingletonHolder{
        static final IUserService HOLDER_INSTANCE = new DefaultUserService();
    }
    public static IUserService getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public User saveUser(User user) {
        return userStorage.saveUser(user);
    }

    @Override
    public List<User> saveUser(List<User> users) {
        return userStorage.saveUser(users);
    }

    @Override
    public boolean deleteUser(long id) {
        return userStorage.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userStorage.updateUser(user);
    }

    @Override
    public User getUser(long id) {
        return userStorage.getUser(id);
    }

    @Override
    public List<User> getUser() {
        return userStorage.getUser();
    }
}
