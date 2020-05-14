package it_academy.control_project.service.impl;

import it_academy.control_project.dao.AuthUserDao;
import it_academy.control_project.dao.impl.DefaultAuthUserDao;
import it_academy.control_project.data.AuthorizationUser;
import it_academy.control_project.service.SecurityService;

public class DefaultSecurityService implements SecurityService {

    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();

    private static class SingletonHolder {
        static final SecurityService HOLDER_INSTANCE = new DefaultSecurityService();
    }
    public static SecurityService getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
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
