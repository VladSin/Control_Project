package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.AuthUserDao;
import it.academy.vladsin.control.project.dao.impl.DefaultAuthUserDao;
import it.academy.vladsin.control.project.data.AuthorizationUser;
import it.academy.vladsin.control.project.service.AuthUserService;

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
    public List<AuthorizationUser> getAuthUsers() {
        return authUserDao.getAuthUsers();
    }
}
