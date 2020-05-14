package it_academy.control_project.dao;

import it_academy.control_project.data.AuthorizationUser;

import java.util.List;

public interface AuthUserDao {

    AuthorizationUser saveAuthUser(AuthorizationUser authorizationUser);

    boolean deleteAuthUser(long id);

    boolean updateAuthUser(AuthorizationUser authorizationUser);

    AuthorizationUser getAuthUser(long id);

    AuthorizationUser getAuthUser(String login);

    List<AuthorizationUser> getAuthUser();
}
