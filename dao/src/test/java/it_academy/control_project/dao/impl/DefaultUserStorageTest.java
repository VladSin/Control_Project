package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.IUserStorage;
import it_academy.control_project.data.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultUserStorageTest {

    IUserStorage userStorage = DefaultUserStorage.getInstance();

    @Test
    void saveUser() {
        final User userToSave = new User(null, "name", "surname", "phone", "email");
        final User savedUser = userStorage.saveUser(userToSave);


        assertEquals(userToSave.getName(), savedUser.getName());
        assertEquals(userToSave.getSurname(), savedUser.getSurname());
        assertEquals(userToSave.getPhone(), savedUser.getPhone());
        assertEquals(userToSave.getEmail(), savedUser.getEmail());
    }

    @Test
    void deleteUser() {
        final User userToSave = new User(null, "name", "surname", "phone", "email");
        final User savedUser = userStorage.saveUser(userToSave);
        final Long id = savedUser.getId();

        final User user = userStorage.getUser(id);
        assertNotNull(user);

        final boolean deleted = userStorage.deleteUser(id);
        assertTrue(deleted);

        final User afterDeleted = userStorage.getUser(id);
        assertNull(afterDeleted);
    }

    @Test
    void updateUser() {
        final User userToSave = new User(null, "name", "surname", "phone", "email");
        final User savedUser = userStorage.saveUser(userToSave);
        final Long id = savedUser.getId();

        final User toUpdate = new User(id, "name", "surname", "phone", "email");
        final boolean update = userStorage.updateUser(toUpdate);
        assertTrue(update);

        final User afterUpdate = userStorage.getUser(id);

        assertEquals(toUpdate.getName(), afterUpdate.getName());
        assertEquals(toUpdate.getSurname(), afterUpdate.getSurname());
        assertEquals(toUpdate.getPhone(), afterUpdate.getPhone());
        assertEquals(toUpdate.getEmail(), afterUpdate.getEmail());
    }

    @Test
    void getUser() {
        final User userToSave = new User(null, "name", "surname", "phone", "email");
        final User savedUser = userStorage.saveUser(userToSave);
        final Long id = savedUser.getId();

        final User user = userStorage.getUser(id);
        assertNotNull(user);
        assertEquals(userToSave.getName(), user.getName());
        assertEquals(userToSave.getSurname(), user.getSurname());
        assertEquals(userToSave.getPhone(), user.getPhone());
        assertEquals(userToSave.getEmail(), user.getEmail());
    }
}