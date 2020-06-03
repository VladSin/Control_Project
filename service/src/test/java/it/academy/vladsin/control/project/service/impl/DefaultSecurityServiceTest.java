package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.AuthUserDao;
import it.academy.vladsin.control.project.data.AuthorizationUser;
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
    AuthUserDao dao;

    @InjectMocks
    DefaultSecurityService service;

    @Test
    void login(){
        when(dao.getAuthUser(1L)).thenReturn(new AuthorizationUser(1L,"User", "User", "role"));
        when(dao.getAuthUser(2L)).thenReturn(new AuthorizationUser(2L,"Teacher", "Teacher", "role"));

        AuthorizationUser userFromDb = service.login("User", "User");
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getLogin(), "User");
        assertEquals(userFromDb.getPassword(),"User");
        assertEquals(userFromDb.getRole(), "role");

        userFromDb = service.login("Teacher", "Teacher");
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getLogin(), "Teacher");
        assertEquals(userFromDb.getPassword(),"Teacher");
        assertEquals(userFromDb.getRole(), "role");
    }
}
