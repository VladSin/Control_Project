package it.academy.control.project.dao.impl;

import it.academy.control.project.dao.AuthUserDao;
import it.academy.control.project.data.AuthorizationUser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    }

    @Test
    void deleteAuthUser() {
        final AuthorizationUser authUserToSave = new AuthorizationUser(null, "login", "password", "role");
        final AuthorizationUser savedAuthUser = authUserDao.saveAuthUser(authUserToSave);
        final Long id = savedAuthUser.getId();
        final AuthorizationUser authorizationUser = authUserDao.getAuthUser(id);
        assertNotNull(authorizationUser);

        final boolean deleted = authUserDao.deleteAuthUser(id);
        assertTrue(deleted);
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
        final boolean updated = authUserDao.updateAuthUser(toUpdate);
        assertTrue(updated);
        authUserDao.updateAuthUser(toUpdate);
        final AuthorizationUser afterUpdate = authUserDao.getAuthUser(id);
        assertEquals(toUpdate.getId(), afterUpdate.getId());
        assertEquals(toUpdate.getLogin(), afterUpdate.getLogin());
        assertEquals(toUpdate.getPassword(), afterUpdate.getPassword());
        assertEquals(toUpdate.getRole(), afterUpdate.getRole());
    }

    @Test
    void getById() {
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

    @Test
    void getList(){
        List<AuthorizationUser> authUser = new ArrayList<>();
        authUser.add(new AuthorizationUser(null, "login1", "pass1", "role1"));
        authUser.add(new AuthorizationUser(null, "login2", "pass2", "role2"));

        List<AuthorizationUser> authUserList = new ArrayList<>();
        for (AuthorizationUser au: authUser) {
            authUserList.add(authUserDao.saveAuthUser(au));
        }

        assertNotNull(authUserList);
        for (int i = 0; i < authUserList.size(); i++) {
            assertEquals(authUserList.get(i).getLogin(), authUser.get(i).getLogin());
            assertEquals(authUserList.get(i).getPassword(), authUser.get(i).getPassword());
            assertEquals(authUserList.get(i).getRole(), authUser.get(i).getRole());
        }
        authUser = authUserDao.getAuthUsers();
        assertNotNull(authUser);
    }
}