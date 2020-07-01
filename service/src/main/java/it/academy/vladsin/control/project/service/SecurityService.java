package it.academy.vladsin.control.project.service;

import it.academy.vladsin.control.project.data.AuthorizationUser;

public interface SecurityService {

    AuthorizationUser login(String login, String password);
}
