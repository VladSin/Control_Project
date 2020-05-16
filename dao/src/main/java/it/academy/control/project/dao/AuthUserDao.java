package it.academy.control.project.dao;

import it.academy.control.project.data.AuthorizationUser;

import java.util.List;

public interface AuthUserDao {

    AuthorizationUser saveAuthUser(AuthorizationUser authorizationUser);

    boolean deleteAuthUser(long id);

    boolean updateAuthUser(AuthorizationUser authorizationUser);

    AuthorizationUser getAuthUser(long id);

    List<AuthorizationUser> getAuthUser();
}
