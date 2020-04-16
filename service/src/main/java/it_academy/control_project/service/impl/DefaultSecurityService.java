package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IAuthUserStorage;
import it_academy.control_project.dao.impl.DefaultAuthUserStorage;
import it_academy.control_project.data.AuthorizationUser;
import it_academy.control_project.service.ISecurityService;

public class DefaultSecurityService implements ISecurityService {

    private IAuthUserStorage authUserStorage = DefaultAuthUserStorage.getInstance();

    private static class SingletonHolder {
        static final ISecurityService HOLDER_INSTANCE = new DefaultSecurityService();
    }
    public static ISecurityService getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public AuthorizationUser login(String login, String password) {
        if (login.equals("User")){
            return authUserStorage.getAuthUser(1);
        } else if (login.equals("Teacher")) {
            return authUserStorage.getAuthUser(2);
        } else {
            return null;
        }
    }
}
