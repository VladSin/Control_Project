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
        when(dao.login("User", "User")).thenReturn(new AuthorizationUser(1L,"User", "User", "role"));
        when(dao.login("Teacher", "Teacher")).thenReturn(new AuthorizationUser(2L,"Teacher", "Teacher", "role"));

        AuthorizationUser userFromDb = dao.login("User", "User");
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getLogin(), "User");
        assertEquals(userFromDb.getPassword(),"User");
        assertEquals(userFromDb.getRole(), "role");

        userFromDb = dao.login("Teacher", "Teacher");
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getLogin(), "Teacher");
        assertEquals(userFromDb.getPassword(),"Teacher");
        assertEquals(userFromDb.getRole(), "role");
    }
}
