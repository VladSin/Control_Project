package it_academy.control_project.dao.converter;

import it_academy.control_project.dao.entity.UserEntity;
import it_academy.control_project.data.User;

public class UserConverter {

    public static User fromEntity(UserEntity userEntity){
        if(userEntity == null){
            return null;
        }
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getPhone(),
                userEntity.getEmail()
        );
    }

    public static UserEntity toEntity(User user){
        if (user == null){
            return null;
        }
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setPhone(user.getPhone());
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }
}
