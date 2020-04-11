package it_academy.control_project.service.impl;

import it_academy.control_project.dao.impl.DefaultAuthUserStorage;
import it_academy.control_project.data.AuthorizationUser;
import it_academy.control_project.service.IAuthUserService;
import it_academy.control_project.service.IUserService;

import java.util.List;

public class DefaultAuthUserService implements IAuthUserService {

    private static class SingletonHolder{
        static final IAuthUserService HOLDER_INSTANCE = new DefaultAuthUserService();
    }
    public static IAuthUserService getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public AuthorizationUser saveAuthUser(AuthorizationUser authorizationUser) {
        return DefaultAuthUserStorage.getInstance().saveAuthUser(authorizationUser);
    }

    @Override
    public List<AuthorizationUser> saveAuthUser(List<AuthorizationUser> authorizationUsers) {
        return DefaultAuthUserStorage.getInstance().saveAuthUser(authorizationUsers);
    }

    @Override
    public boolean deleteAuthUser(long id) {
        return DefaultAuthUserStorage.getInstance().deleteAuthUser(id);
    }

    @Override
    public boolean updateAuthUser(AuthorizationUser authorizationUser) {
        return DefaultAuthUserStorage.getInstance().updateAuthUser(authorizationUser);
    }

    @Override
    public AuthorizationUser getAuthUser(long id) {
        return DefaultAuthUserStorage.getInstance().getAuthUser(id);
    }

    @Override
    public List<AuthorizationUser> getAuthUser() {
        return DefaultAuthUserStorage.getInstance().getAuthUser();
    }

    @Override
    public AuthorizationUser getLogin(String login) {
        return DefaultAuthUserStorage.getInstance().getLogin(login);
    }
}
