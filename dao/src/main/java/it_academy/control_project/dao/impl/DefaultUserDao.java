package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.UserDao;
import it_academy.control_project.dao.converter.UserConverter;
import it_academy.control_project.dao.entity.UserEntity;
import it_academy.control_project.dao.util.HibernateUtil;
import it_academy.control_project.data.User;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultUserDao implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserDao.class);

    private static class SingletonHolder{
        static final UserDao HOLDER_INSTANCE = new DefaultUserDao();
    }
    public static UserDao getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();
        log.info("user saved:{}", user);
        return user;
    }

    @Override
    public boolean deleteUser(long id) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete from UserEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(userEntity);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public User getUser(long id) {
        final Session session = HibernateUtil.getSession();
        UserEntity userEntity = session.load(UserEntity.class, id);
        return UserConverter.fromEntity(userEntity);
    }

    @Override
    public List<User> getUser() {
        final List<UserEntity> userEntities = HibernateUtil.getSession().createQuery("from UserEntity ")
                .list();
        return userEntities.stream()
                .map(UserConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
