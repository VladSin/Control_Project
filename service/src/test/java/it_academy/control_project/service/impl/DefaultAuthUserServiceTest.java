package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IAuthUserStorage;
import it_academy.control_project.data.AuthorizationUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultAuthUserServiceTest {

    @Mock
    IAuthUserStorage dao;

    @InjectMocks
    DefaultAuthUserService service;

    @Test
    void getNonExistentUser(){
        when(dao.getAuthUser(0L)).thenReturn(null);
        AuthorizationUser user = service.getAuthUser(0L);
        assertNull(user);
    }

    @Test
    void getExistingUser(){
        when(dao.getAuthUser(1)).thenReturn(new AuthorizationUser(1L,"login", "password", "role"));
        AuthorizationUser userFromDb = service.getAuthUser(1);
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getLogin(), "login");
        assertEquals(userFromDb.getPassword(), "password");
        assertEquals(userFromDb.getRole(), "role");
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getAuthUser(2L)).thenReturn(null);
        AuthorizationUser userFromDb = service.getAuthUser(2L);
        assertNull(userFromDb);
    }

    @Test
    void getUser(){
        when(dao.getAuthUser(1)).thenReturn(new AuthorizationUser(1L,"login", "password", "role"));
        final AuthorizationUser userFromDb = service.getAuthUser(1);
        assertNotNull(userFromDb);

        final AuthorizationUser user = service.getAuthUser(1);
        assertNotNull(user);
        assertEquals(userFromDb.getId(), user.getId());
        assertEquals(userFromDb.getLogin(), user.getLogin());
        assertEquals(userFromDb.getPassword(), user.getPassword());
        assertEquals(userFromDb.getRole(), user.getRole());
    }
}