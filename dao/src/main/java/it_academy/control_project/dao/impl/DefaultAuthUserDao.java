package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.AuthUserDao;
import it_academy.control_project.dao.converter.AuthorizationUserConverter;
import it_academy.control_project.dao.entity.AuthorizationUserEntity;
import it_academy.control_project.dao.util.HibernateUtil;
import it_academy.control_project.data.AuthorizationUser;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultAuthUserDao implements AuthUserDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultAuthUserDao.class);

    private static class SingletonHolder {
        static final AuthUserDao HOLDER_INSTANCE = new DefaultAuthUserDao();
    }
    public static AuthUserDao getInstance(){
        return SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public AuthorizationUser saveAuthUser(AuthorizationUser authorizationUser) {
        AuthorizationUserEntity authorizationUserEntity = AuthorizationUserConverter.toEntity(authorizationUser);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(authorizationUserEntity);
        session.getTransaction().commit();
        log.info("authUser saved:{}", authorizationUser);
        return authorizationUser;
    }

    @Override
    public boolean deleteAuthUser(long id) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete from AuthorizationUserEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateAuthUser(AuthorizationUser authorizationUser) {
        AuthorizationUserEntity authorizationUserEntity = AuthorizationUserConverter.toEntity(authorizationUser);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(authorizationUserEntity);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public AuthorizationUser getAuthUser(long id) {
        final Session session = HibernateUtil.getSession();
        AuthorizationUserEntity authUserEntity = session.load(AuthorizationUserEntity.class, id);
        return AuthorizationUserConverter.fromEntity(authUserEntity);
    }

    @Override
    public AuthorizationUser getAuthUser(String login) {
        AuthorizationUserEntity authUser;
        try {
            authUser = (AuthorizationUserEntity) HibernateUtil.getSession().createQuery("from AuthorizationUserEntity au where au.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.info("user not found by login{}", login);
            authUser = null;
        }
        return AuthorizationUserConverter.fromEntity(authUser);
    }

    @Override
    public List<AuthorizationUser> getAuthUser() {
        final List<AuthorizationUserEntity> authUser = HibernateUtil.getSession().createQuery("from AuthorizationUserEntity")
                .list();
        return authUser.stream()
                .map(AuthorizationUserConverter::fromEntity)
                .collect(Collectors.toList());
    }
}