package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IAuthUserStorage;
import it_academy.control_project.data.AuthorizationUser;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DefaultSecurityServiceTest {
    @Mock
    IAuthUserStorage dao;

    @InjectMocks
    DefaultSecurityService service;

    @Test
    void testLoginNotExist() {
        when(dao.getLogin("User")).thenReturn(null);
        AuthorizationUser login = service.login("User", "User");
        assertNull(login);
    }

    @Test
    void testLoginCorrect() {
        when(dao.getLogin("User")).thenReturn(new AuthorizationUser(null, "User", "User", null));
        AuthorizationUser userFromDb = service.login("User", "User");
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getLogin(), "User");
        assertNotNull(userFromDb.getPassword(), "User");
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getLogin("User")).thenReturn(new AuthorizationUser(null, "User", "User", null));
        AuthorizationUser login = service.login("User", "User2");
        assertNull(login);
    }

}