package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IUserStorage;
import it_academy.control_project.data.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultUserServiceTest {

    @Mock
    IUserStorage dao;

    @InjectMocks
    DefaultUserService service;

    @Test
    void getNonExistentUser(){
        when(dao.getUser(0)).thenReturn(null);
        User user = service.getUser(0);
        assertNull(user);
    }

    @Test
    void getExistingUser(){
        when(dao.getUser(1)).thenReturn(new User(1L, "User", "User", "+375299999999", "user@mail.ru"));
        User userFromDb = service.getUser(1);
        assertNotNull(userFromDb);
        assertEquals(userFromDb.getName(), "User");
        assertEquals(userFromDb.getSurname(), "User");
        assertEquals(userFromDb.getPhone(), "+375299999999");
        assertEquals(userFromDb.getEmail(), "user@mail.ru");
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getUser(1)).thenReturn(new User(1L, "User", "User", "+375299999999", "user@mail.ru"));
        User userFromDb = service.getUser(2);
        assertNull(userFromDb);
    }

    @Test
    void deleteUser(){
        when(dao.getUser(1)).thenReturn(new User(1L, "User", "User", "+375299999999", "user@mail.ru"));
        final User userFromDb = service.getUser(1);
        assertNotNull(userFromDb);

        final boolean deleted = service.deleteUser(1);
        assertTrue(deleted);

        final User afterDeleted = service.getUser(1);
        assertNull(afterDeleted);
    }

    @Test
    void getUser(){
        when(dao.getUser(1)).thenReturn(new User(1L, "User", "User", "+375299999999", "user@mail.ru"));
        final User userFromDb = service.getUser(1);
        assertNotNull(userFromDb);

        final User user = service.getUser(1);
        assertNotNull(user);
        assertEquals(userFromDb.getId(), user.getId());
        assertEquals(userFromDb.getName(), user.getName());
        assertEquals(userFromDb.getSurname(), user.getSurname());
        assertEquals(userFromDb.getPhone(), user.getPhone());
        assertEquals(userFromDb.getEmail(), user.getEmail());
    }
}