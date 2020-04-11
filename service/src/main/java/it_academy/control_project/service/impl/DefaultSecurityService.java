package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IAuthUserStorage;
import it_academy.control_project.dao.impl.DefaultAuthUserStorage;
import it_academy.control_project.data.AuthorizationUser;
import it_academy.control_project.service.IAuthUserService;
import it_academy.control_project.service.ISecurityService;

public class DefaultSecurityService implements ISecurityService {

    private static class SingletonHolder {
        static final ISecurityService HOLDER_INSTANCE = new DefaultSecurityService();
    }
    public static ISecurityService getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private IAuthUserStorage authUserStorage = DefaultAuthUserStorage.getInstance();
    private IAuthUserService authUserService = DefaultAuthUserService.getInstance();

    @Override
    public AuthorizationUser login(String login, String password) {
        AuthorizationUser user = authUserStorage.getLogin(login);
        if (user == null){
            return null;
        }
        for (int i = 1; i < 3; i++) {
            AuthorizationUser authUser = authUserService.getAuthUser(i);
            if (user.getPassword().equals(authUser.getPassword())){
                return user;
            }
        }
        return null;
    }
}
