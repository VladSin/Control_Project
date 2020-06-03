package it.academy.vladsin.control.project.dao.converter;

import it.academy.vladsin.control.project.dao.entity.AuthorizationUserEntity;
import it.academy.vladsin.control.project.data.AuthorizationUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthUserConverterTest {

    @Test
    void fromEntity(){
        AuthorizationUserEntity authUserEntity = new AuthorizationUserEntity();
        authUserEntity.setId(null);
        authUserEntity.setLogin("login");
        authUserEntity.setPassword("pass");
        authUserEntity.setRole("role");

        AuthorizationUser authorizationUser = AuthorizationUserConverter.fromEntity(authUserEntity);
        assertNotNull(authorizationUser);
        assertEquals(authorizationUser.getLogin(), authUserEntity.getLogin());
        assertEquals(authorizationUser.getPassword(), authUserEntity.getPassword());
        assertEquals(authorizationUser.getRole(), authUserEntity.getRole());
    }

    @Test
    void toEntity(){
        AuthorizationUser authUser = new AuthorizationUser(null, "login", "pass", "role");
        AuthorizationUserEntity authUserEntity = AuthorizationUserConverter.toEntity(authUser);
        assertNotNull(authUserEntity);
        assertEquals(authUser.getLogin(), authUserEntity.getLogin());
        assertEquals(authUser.getPassword(), authUserEntity.getPassword());
        assertEquals(authUser.getRole(), authUserEntity.getRole());
    }
}
