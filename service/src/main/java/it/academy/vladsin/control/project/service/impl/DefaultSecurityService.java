package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.AuthUserDao;
import it.academy.vladsin.control.project.data.AuthorizationUser;
import it.academy.vladsin.control.project.service.SecurityService;
import org.springframework.transaction.annotation.Transactional;

public class DefaultSecurityService implements SecurityService {

    private final AuthUserDao authUserDao;

    public DefaultSecurityService(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }

    @Override
    @Transactional
    public AuthorizationUser login(String login, String password) {
        if (login.equals("User")){
            return authUserDao.getAuthUser(1);
        } else if (login.equals("Teacher")) {
            return authUserDao.getAuthUser(2);
        } else {
            return null;
        }
    }
}
