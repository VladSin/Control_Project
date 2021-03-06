package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.UserDao;
import it.academy.vladsin.control.project.dao.config.DaoConfig;
import it.academy.vladsin.control.project.data.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
@Commit
class DefaultUserDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    void saveUser() {
        final User userToSave = new User(null, "name", "surname", "phone", "email");
        final User savedUser = userDao.saveUser(userToSave);

        assertEquals(userToSave.getName(), savedUser.getName());
        assertEquals(userToSave.getSurname(), savedUser.getSurname());
        assertEquals(userToSave.getPhone(), savedUser.getPhone());
        assertEquals(userToSave.getEmail(), savedUser.getEmail());
    }

    @Test
    void deleteUser() {
        final User userToSave = new User(null, "name", "surname", "phone", "email");
        final User savedUser = userDao.saveUser(userToSave);
        final Long id = savedUser.getId();
        sessionFactory.getCurrentSession().clear();

        final boolean deleted = userDao.deleteUser(id);
        assertTrue(deleted);

        final User afterDeleted = userDao.getUser(id);
        assertNull(afterDeleted);
    }

    @Test
    void updateUser() {
        final User userToSave = new User(null, "name", "surname", "phone", "email");
        final User savedUser = userDao.saveUser(userToSave);
        final Long id = savedUser.getId();
        sessionFactory.getCurrentSession().clear();

        final User toUpdate = new User(id, "name", "surname", "phone", "email");
        final boolean update = userDao.updateUser(toUpdate);
        assertTrue(update);

        final User afterUpdate = userDao.getUser(id);

        assertEquals(toUpdate.getName(), afterUpdate.getName());
        assertEquals(toUpdate.getSurname(), afterUpdate.getSurname());
        assertEquals(toUpdate.getPhone(), afterUpdate.getPhone());
        assertEquals(toUpdate.getEmail(), afterUpdate.getEmail());
    }

    @Test
    void getUser() {
        final User userToSave = new User(null, "name", "surname", "phone", "email");
        final User savedUser = userDao.saveUser(userToSave);
        final Long id = savedUser.getId();

        final User user = userDao.getUser(id);
        assertNotNull(user);
        assertEquals(userToSave.getName(), user.getName());
        assertEquals(userToSave.getSurname(), user.getSurname());
        assertEquals(userToSave.getPhone(), user.getPhone());
        assertEquals(userToSave.getEmail(), user.getEmail());
    }

    @Test
    void getList(){
        List<User> users = new ArrayList<>();
        users.add(new User(null, "User1", "User1", "+375299999999", "user1@mail.ru"));
        users.add(new User(null, "User2", "User2", "+375299999999", "user2@mail.ru"));

        List<User> userList = new ArrayList<>();
        for (User u: users) {
            userList.add(userDao.saveUser(u));
        }

        assertNotNull(userList);
        for (int i = 0; i < userList.size(); i++) {
            assertEquals(userList.get(i).getName(), users.get(i).getName());
            assertEquals(userList.get(i).getSurname(), users.get(i).getSurname());
            assertEquals(userList.get(i).getPhone(), users.get(i).getPhone());
            assertEquals(userList.get(i).getEmail(), users.get(i).getEmail());
        }

        users = userDao.getUsers();
        assertNotNull(users);
    }
}