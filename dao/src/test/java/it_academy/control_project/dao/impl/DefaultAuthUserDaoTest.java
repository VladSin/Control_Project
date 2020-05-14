package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.AuthUserDao;
import it_academy.control_project.data.AuthorizationUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultAuthUserDaoTest {

    AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();

    @Test
    void saveAuthUser() {
        final AuthorizationUser authUserToSave = new AuthorizationUser(null, "login", "password", "role");
        final AuthorizationUser savedAuthUser = authUserDao.saveAuthUser(authUserToSave);
        assertEquals(authUserToSave.getLogin(), savedAuthUser.getLogin());
        assertEquals(authUserToSave.getPassword(), savedAuthUser.getPassword());
        assertEquals(authUserToSave.getRole(), savedAuthUser.getRole());
        assertNotNull(savedAuthUser.getId());
    }

    @Test
    void deleteAuthUser() {
        final AuthorizationUser authUserToSave = new AuthorizationUser(null, "login", "password", "role");
        final AuthorizationUser savedAuthUser = authUserDao.saveAuthUser(authUserToSave);
        final Long id = savedAuthUser.getId();
        final AuthorizationUser authorizationUser = authUserDao.getAuthUser(id);
        assertNotNull(authorizationUser);

        //final boolean deleted = authUserStorage.deleteAuthUser(id);
        //assertTrue(deleted);
        authUserDao.deleteAuthUser(id);
        final AuthorizationUser afterDelete = authUserDao.getAuthUser(id);
        assertNull(afterDelete);
    }

    @Test
    void updateAuthUser() {
        final AuthorizationUser authUserToSave = new AuthorizationUser(null, "login", "password", "role");
        final AuthorizationUser savedAuthUser = authUserDao.saveAuthUser(authUserToSave);
        final Long id = savedAuthUser.getId();

        final AuthorizationUser toUpdate = new AuthorizationUser(id, "login2", "password2", "role2");
        //final boolean updated = authUserStorage.updateAuthUser(toUpdate);
        //assertTrue(updated);
        authUserDao.updateAuthUser(toUpdate);
        final AuthorizationUser afterUpdate = authUserDao.getAuthUser(id);
        assertEquals(toUpdate.getId(), afterUpdate.getId());
        assertEquals(toUpdate.getLogin(), afterUpdate.getLogin());
        assertEquals(toUpdate.getPassword(), afterUpdate.getPassword());
        assertEquals(toUpdate.getRole(), afterUpdate.getRole());
    }

    @Test
    void getAuthUser() {
        final AuthorizationUser authUserToSave = new AuthorizationUser(null, "login", "password", "role");
        final AuthorizationUser savedAuthUser = authUserDao.saveAuthUser(authUserToSave);
        final Long id = savedAuthUser.getId();

        final AuthorizationUser authorizationUser = authUserDao.getAuthUser(id);
        assertNotNull(authorizationUser);

        assertEquals(authUserToSave.getLogin(), authorizationUser.getLogin());
        assertEquals(authUserToSave.getPassword(), authorizationUser.getPassword());
        assertEquals(authUserToSave.getRole(), authorizationUser.getRole());
        assertEquals(id, authorizationUser.getId());
    }

    /*@Test
    void getLogin() {
        final AuthorizationUser authUserToSave = new AuthorizationUser(null, "login", "password", "role");
        final AuthorizationUser savedAuthUser = authUserStorage.saveAuthUser(authUserToSave);
        final String login = savedAuthUser.getLogin();

        final AuthorizationUser authorizationUser = authUserStorage.getLogin(login);
        assertNotNull(authorizationUser);

        assertEquals(authUserToSave.getLogin(), authorizationUser.getLogin());
        assertEquals(authUserToSave.getPassword(), authorizationUser.getPassword());
        assertEquals(authUserToSave.getRole(), authorizationUser.getRole());
        assertEquals(login, authorizationUser.getLogin());
    }*/
}