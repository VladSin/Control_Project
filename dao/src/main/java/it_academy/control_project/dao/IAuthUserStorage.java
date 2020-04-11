package it_academy.control_project.dao;

import it_academy.control_project.data.AuthorizationUser;

public interface IAuthUserStorage {

    AuthorizationUser getLogin(String login);
    Long saveAuthUser(AuthorizationUser user);
}
