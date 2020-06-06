package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.UserDao;
import it.academy.vladsin.control.project.dao.converter.UserConverter;
import it.academy.vladsin.control.project.dao.entity.UserEntity;
import it.academy.vladsin.control.project.data.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultUserDao implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserDao.class);

    private final SessionFactory factory;

    public DefaultUserDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        final Session session = factory.getCurrentSession();
        session.save(userEntity);
        log.info("user saved:{}", user);
        return UserConverter.fromEntity(userEntity);
    }

    @Override
    public boolean deleteUser(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from UserEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        final Session session = factory.getCurrentSession();
        session.update(userEntity);
        return true;
    }

    @Override
    public User getUser(long id) {
        final Session session = factory.getCurrentSession();
        UserEntity userEntity = session.get(UserEntity.class, id);
        try {
            User user = UserConverter.fromEntity(userEntity);
            return user;
        } catch (RuntimeException e){
            log.info("user not found by id{}", id);
            return null;
        }
    }

    @Override
    public List<User> getUsers() {
        final List<UserEntity> userEntities = factory.getCurrentSession().createQuery("from UserEntity ")
                .list();
        return userEntities.stream()
                .map(UserConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
