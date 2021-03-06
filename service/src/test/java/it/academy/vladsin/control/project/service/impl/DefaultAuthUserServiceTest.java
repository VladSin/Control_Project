package it.academy.vladsin.control.project.service.impl;

import it.academy.vladsin.control.project.dao.AuthUserDao;
import it.academy.vladsin.control.project.data.AuthorizationUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultAuthUserServiceTest {

    @Mock
    AuthUserDao dao;

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

    @Test
    void saveUser(){
        when(dao.getAuthUser(1)).thenReturn(new AuthorizationUser(1L,"login", "password", "role"));
        final AuthorizationUser userFromDb = service.getAuthUser(1);
        assertNotNull(userFromDb);

        when(dao.saveAuthUser(userFromDb)).thenReturn(userFromDb);
        final AuthorizationUser user = service.saveAuthUser(userFromDb);

        assertNotNull(user);
        assertEquals(userFromDb.getId(), user.getId());
        assertEquals(userFromDb.getLogin(), user.getLogin());
        assertEquals(userFromDb.getPassword(), user.getPassword());
        assertEquals(userFromDb.getRole(), user.getRole());
    }

    @Test
    void deleteUser(){
        when(dao.getAuthUser(1)).thenReturn(new AuthorizationUser(1L,"login", "password", "role"));
        final AuthorizationUser userFromDb = service.getAuthUser(1);
        assertNotNull(userFromDb);

        when(dao.deleteAuthUser(1L)).thenReturn(true);
        final boolean delete = service.deleteAuthUser(1L);
        assertTrue(delete);
    }

    @Test
    void updateUser(){
        when(dao.getAuthUser(1)).thenReturn(new AuthorizationUser(1L,"login", "password", "role"));
        final AuthorizationUser userFromDb = service.getAuthUser(1);
        assertNotNull(userFromDb);

        when(dao.updateAuthUser(userFromDb)).thenReturn(true);
        final boolean update = service.updateAuthUser(userFromDb);
        assertTrue(update);
    }

    @Test
    void getList(){
        List<AuthorizationUser> authorizationUserList = new ArrayList<>();
        authorizationUserList.add(new AuthorizationUser(1L, "login1", "password1", "role1"));
        authorizationUserList.add(new AuthorizationUser(2L, "login2", "password2", "role2"));
        when(dao.getAuthUsers()).thenReturn(authorizationUserList);

        List<AuthorizationUser> authorizationUsersDao = dao.getAuthUsers();
        assertNotNull(authorizationUsersDao);
        for (int i = 0; i < authorizationUserList.size(); i++) {
            assertEquals(authorizationUsersDao.get(i).getId(), authorizationUserList.get(i).getId());
            assertEquals(authorizationUsersDao.get(i).getLogin(), authorizationUserList.get(i).getLogin());
            assertEquals(authorizationUsersDao.get(i).getPassword(), authorizationUserList.get(i).getPassword());
            assertEquals(authorizationUsersDao.get(i).getRole(), authorizationUserList.get(i).getRole());
        }
    }
}