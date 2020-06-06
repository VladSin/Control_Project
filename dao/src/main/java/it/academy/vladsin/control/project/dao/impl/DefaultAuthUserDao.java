package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.entity.AuthorizationUserEntity;
import it.academy.vladsin.control.project.dao.AuthUserDao;
import it.academy.vladsin.control.project.dao.converter.AuthorizationUserConverter;
import it.academy.vladsin.control.project.data.AuthorizationUser;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultAuthUserDao implements AuthUserDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultAuthUserDao.class);

    private final SessionFactory factory;

    public DefaultAuthUserDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public AuthorizationUser saveAuthUser(AuthorizationUser authorizationUser) {
        AuthorizationUserEntity authorizationUserEntity = AuthorizationUserConverter.toEntity(authorizationUser);
        final Session session = factory.getCurrentSession();
        session.save(authorizationUserEntity);
        log.info("authUser saved:{}", authorizationUser);
        return AuthorizationUserConverter.fromEntity(authorizationUserEntity);
    }

    @Override
    public boolean deleteAuthUser(long id) {
        final Session session = factory.getCurrentSession();
        session.createQuery("delete from AuthorizationUserEntity as a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return true;
    }

    @Override
    public boolean updateAuthUser(AuthorizationUser authorizationUser) {
        AuthorizationUserEntity authorizationUserEntity = AuthorizationUserConverter.toEntity(authorizationUser);
        final Session session = factory.getCurrentSession();
        session.update(authorizationUserEntity);
        return true;
    }

    @Override
    public AuthorizationUser getAuthUser(long id) {
        final Session session = factory.getCurrentSession();
        AuthorizationUserEntity authUserEntity = session.get(AuthorizationUserEntity.class, id);
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
        Query<AuthorizationUserEntity> query = factory.getCurrentSession().createQuery("from AuthorizationUserEntity")
                .setCacheable(true);
        return query.stream()
                .map(AuthorizationUserConverter::fromEntity)
                .collect(Collectors.toList());
    }
}