package it.academy.control.project.service.impl;

import it.academy.control.project.data.AuthorizationUser;
import it.academy.control.project.service.SecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultSecurityServiceTest {

    @Mock
    SecurityService dao;

    @InjectMocks
    DefaultSecurityService service;

    @Test
    void login(){
        when(dao.login("login", "password")).thenReturn(new AuthorizationUser(1L,"login", "password", "role"));
        final AuthorizationUser userFromDb = dao.login("login", "password");
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getLogin(), "login");
        assertEquals(userFromDb.getPassword(),"password");
        assertEquals(userFromDb.getRole(), "role");
    }
}
