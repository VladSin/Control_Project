package it.academy.control.project.service;

import it.academy.control.project.data.AuthorizationUser;

public interface SecurityService {

    AuthorizationUser login(String login, String password);
}
