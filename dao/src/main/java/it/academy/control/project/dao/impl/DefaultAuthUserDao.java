package it.academy.control.project.dao.impl;

import it.academy.control.project.dao.entity.AuthorizationUserEntity;
import it.academy.control.project.dao.AuthUserDao;
import it.academy.control.project.dao.converter.AuthorizationUserConverter;
import it.academy.control.project.dao.util.HibernateUtil;
import it.academy.control.project.data.AuthorizationUser;

import org.hibernate.Session;
import org.hibernate.query.Query;
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
        return AuthorizationUserConverter.fromEntity(authorizationUserEntity);
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
        try {
            AuthorizationUser authorizationUser = AuthorizationUserConverter.fromEntity(authUserEntity);
            return authorizationUser;
        } catch (RuntimeException e){
            log.info("authUser not found by id{}", id);
            return null;
        }
    }

    @Override
    public List<AuthorizationUser> getAuthUsers() {
        Query<AuthorizationUserEntity> query = HibernateUtil.getSession().createQuery("from AuthorizationUserEntity")
                .setCacheable(true);
        return query.stream()
                .map(AuthorizationUserConverter::fromEntity)
                .collect(Collectors.toList());
    }
}