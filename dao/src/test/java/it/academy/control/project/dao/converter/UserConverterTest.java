package it.academy.control.project.dao.converter;

import it.academy.control.project.dao.entity.UserEntity;
import it.academy.control.project.data.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserConverterTest {

    @Test
    void fromEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(null);
        userEntity.setName("name");
        userEntity.setSurname("surname");
        userEntity.setPhone("phone");
        userEntity.setEmail("email");

        User user = UserConverter.fromEntity(userEntity);
        assertNotNull(user);
        assertEquals(user.getName(), userEntity.getName());
        assertEquals(user.getSurname(), userEntity.getSurname());
        assertEquals(user.getPhone(), userEntity.getPhone());
        assertEquals(user.getEmail(), userEntity.getEmail());
    }

    @Test
    void toEntity(){
        User user = new User(null, "name", "surname", "phone", "email");
        UserEntity userEntity = UserConverter.toEntity(user);
        assertNotNull(userEntity);
        assertEquals(user.getName(), userEntity.getName());
        assertEquals(user.getSurname(), userEntity.getSurname());
        assertEquals(user.getPhone(), userEntity.getPhone());
        assertEquals(user.getEmail(), userEntity.getEmail());
    }
}
