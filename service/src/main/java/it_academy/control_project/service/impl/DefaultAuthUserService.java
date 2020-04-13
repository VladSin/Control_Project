package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IAuthUserStorage;
import it_academy.control_project.dao.impl.DefaultAuthUserStorage;
import it_academy.control_project.data.AuthorizationUser;
import it_academy.control_project.service.IAuthUserService;

import java.util.List;

public class DefaultAuthUserService implements IAuthUserService {

    private IAuthUserStorage authUserStorage = DefaultAuthUserStorage.getInstance();

    private static class SingletonHolder{
        static final IAuthUserService HOLDER_INSTANCE = new DefaultAuthUserService();
    }
    public static IAuthUserService getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public AuthorizationUser saveAuthUser(AuthorizationUser authorizationUser) {
        return authUserStorage.saveAuthUser(authorizationUser);
    }

    @Override
    public List<AuthorizationUser> saveAuthUser(List<AuthorizationUser> authorizationUsers) {
        return authUserStorage.saveAuthUser(authorizationUsers);
    }

    @Override
    public boolean deleteAuthUser(long id) {
        return authUserStorage.deleteAuthUser(id);
    }

    @Override
    public boolean updateAuthUser(AuthorizationUser authorizationUser) {
        return authUserStorage.updateAuthUser(authorizationUser);
    }

    @Override
    public AuthorizationUser getAuthUser(long id) {
        return authUserStorage.getAuthUser(id);
    }

    @Override
    public List<AuthorizationUser> getAuthUser() {
        return authUserStorage.getAuthUser();
    }

    @Override
    public AuthorizationUser getLogin(String login) {
        return authUserStorage.getLogin(login);
    }
}
