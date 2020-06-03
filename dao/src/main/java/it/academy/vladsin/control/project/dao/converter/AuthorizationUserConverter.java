package it.academy.vladsin.control.project.dao.converter;

import it.academy.vladsin.control.project.dao.entity.AuthorizationUserEntity;
import it.academy.vladsin.control.project.data.AuthorizationUser;

public class AuthorizationUserConverter {

    public static AuthorizationUser fromEntity(AuthorizationUserEntity authorizationUserEntity){
        if(authorizationUserEntity == null){
            return null;
        }
        return new AuthorizationUser(
                authorizationUserEntity.getId(),
                authorizationUserEntity.getLogin(),
                authorizationUserEntity.getPassword(),
                authorizationUserEntity.getRole()
        );
    }

    public static AuthorizationUserEntity toEntity(AuthorizationUser authorizationUser){
        if (authorizationUser == null){
            return null;
        }
        final AuthorizationUserEntity authorizationUserEntity = new AuthorizationUserEntity();
        authorizationUserEntity.setId(authorizationUser.getId());
        authorizationUserEntity.setLogin(authorizationUser.getLogin());
        authorizationUserEntity.setPassword(authorizationUser.getPassword());
        authorizationUserEntity.setRole(authorizationUser.getRole());
        return authorizationUserEntity;
    }
}
