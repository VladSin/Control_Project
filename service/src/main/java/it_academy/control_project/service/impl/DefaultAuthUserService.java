package it_academy.control_project.service.impl;

import it_academy.control_project.dao.AuthUserDao;
import it_academy.control_project.dao.impl.DefaultAuthUserDao;
import it_academy.control_project.data.AuthorizationUser;
import it_academy.control_project.service.AuthUserService;

import java.util.List;

public class DefaultAuthUserService implements AuthUserService {

    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();

    private static class SingletonHolder{
        static final AuthUserService HOLDER_INSTANCE = new DefaultAuthUserService();
    }
    public static AuthUserService getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public AuthorizationUser saveAuthUser(AuthorizationUser authorizationUser) {
        return authUserDao.saveAuthUser(authorizationUser);
    }

    @Override
    public boolean deleteAuthUser(long id) {
        return authUserDao.deleteAuthUser(id);
    }

    @Override
    public boolean updateAuthUser(AuthorizationUser authorizationUser) {
        return authUserDao.updateAuthUser(authorizationUser);
    }

    @Override
    public AuthorizationUser getAuthUser(long id) {
        return authUserDao.getAuthUser(id);
    }

    @Override
    public AuthorizationUser getAuthUser(String login) {
        return authUserDao.getAuthUser(login);
    }

    @Override
    public List<AuthorizationUser> getAuthUser() {
        return authUserDao.getAuthUser();
    }
}
