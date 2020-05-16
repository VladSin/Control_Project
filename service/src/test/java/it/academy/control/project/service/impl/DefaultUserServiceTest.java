package it.academy.control.project.service.impl;

import it.academy.control.project.dao.UserDao;
import it.academy.control.project.data.User;
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
class DefaultUserServiceTest {

    @Mock
    UserDao dao;

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
        when(dao.getUser(1L)).thenReturn(null);
        User userFromDb = service.getUser(1L);
        assertNull(userFromDb);
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

    @Test
    void saveUser(){
        when(dao.getUser(1)).thenReturn(new User(1L, "User", "User", "+375299999999", "user@mail.ru"));
        final User userFromDb = service.getUser(1);
        assertNotNull(userFromDb);

        when(dao.saveUser(userFromDb)).thenReturn(userFromDb);
        final User user = service.saveUser(userFromDb);

        assertNotNull(user);
        assertEquals(userFromDb.getId(), user.getId());
        assertEquals(userFromDb.getName(), user.getName());
        assertEquals(userFromDb.getSurname(), user.getSurname());
        assertEquals(userFromDb.getPhone(), user.getPhone());
        assertEquals(userFromDb.getEmail(), user.getEmail());
    }

    @Test
    void deleteUser(){
        when(dao.getUser(1)).thenReturn(new User(1L, "User", "User", "+375299999999", "user@mail.ru"));
        final User userFromDb = service.getUser(1);
        assertNotNull(userFromDb);

        when(dao.deleteUser(1L)).thenReturn(true);
        final boolean delete = service.deleteUser(1L);
        assertTrue(delete);
    }

    @Test
    void updateUser(){
        when(dao.getUser(1)).thenReturn(new User(1L, "User", "User", "+375299999999", "user@mail.ru"));
        final User userFromDb = service.getUser(1);
        assertNotNull(userFromDb);

        when(dao.updateUser(userFromDb)).thenReturn(true);
        final boolean update = service.updateUser(userFromDb);
        assertTrue(update);
    }

    @Test
    void getList(){
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "User1", "User1", "+375299999999", "user1@mail.ru"));
        users.add(new User(2L, "User2", "User2", "+375299999999", "user2@mail.ru"));
        when(dao.getUser()).thenReturn(users);

        List<User> userDao = dao.getUser();
        assertNotNull(userDao);
        for (int i = 0; i < userDao.size(); i++) {
            assertEquals(userDao.get(i).getId(), users.get(i).getId());
            assertEquals(userDao.get(i).getName(), users.get(i).getName());
            assertEquals(userDao.get(i).getSurname(), users.get(i).getSurname());
            assertEquals(userDao.get(i).getPhone(), users.get(i).getPhone());
            assertEquals(userDao.get(i).getEmail(), users.get(i).getEmail());
        }
    }
}