package it_academy.control_project.service;

import it_academy.control_project.data.AuthorizationUser;

import java.util.List;

public interface IAuthUserService {

    AuthorizationUser saveAuthUser(AuthorizationUser authorizationUser);
    List<AuthorizationUser> saveAuthUser(List<AuthorizationUser> authorizationUsers);

    boolean deleteAuthUser(long id);
    boolean updateAuthUser(AuthorizationUser authorizationUser);

    AuthorizationUser getAuthUser(long id);
    List<AuthorizationUser> getAuthUser();

    AuthorizationUser getLogin(String login);
}
