package it_academy.control_project.service;

import it_academy.control_project.data.AuthorizationUser;

public interface ISecurityService {
    AuthorizationUser login(String login, String password);
}
