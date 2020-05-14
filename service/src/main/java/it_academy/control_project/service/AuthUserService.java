package it_academy.control_project.service;

import it_academy.control_project.data.AuthorizationUser;

import java.util.List;

public interface AuthUserService {

    AuthorizationUser saveAuthUser(AuthorizationUser authorizationUser);

    boolean deleteAuthUser(long id);

    boolean updateAuthUser(AuthorizationUser authorizationUser);

    AuthorizationUser getAuthUser(long id);

    AuthorizationUser getAuthUser(String login);

    List<AuthorizationUser> getAuthUser();
}
