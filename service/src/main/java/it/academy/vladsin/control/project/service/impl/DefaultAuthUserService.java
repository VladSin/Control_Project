package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.AuthUserDao;
import it.academy.vladsin.control.project.data.AuthorizationUser;
import it.academy.vladsin.control.project.service.AuthUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultAuthUserService implements AuthUserService {

    private final AuthUserDao authUserDao;

    public DefaultAuthUserService(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }

    @Override
    @Transactional
    public AuthorizationUser saveAuthUser(AuthorizationUser authorizationUser) {
        return authUserDao.saveAuthUser(authorizationUser);
    }

    @Override
    @Transactional
    public boolean deleteAuthUser(long id) {
        return authUserDao.deleteAuthUser(id);
    }

    @Override
    @Transactional
    public boolean updateAuthUser(AuthorizationUser authorizationUser) {
        return authUserDao.updateAuthUser(authorizationUser);
    }

    @Override
    @Transactional
    public AuthorizationUser getAuthUser(long id) {
        return authUserDao.getAuthUser(id);
    }

    @Override
    @Transactional
    public List<AuthorizationUser> getAuthUsers() {
        return authUserDao.getAuthUsers();
    }
}
