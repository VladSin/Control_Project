package it.academy.control.project.dao.util;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static EntityManagerFactory entityManagerFactory = null;

    public static EntityManager getEm(){
        if (entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("control.project");
        }
        return entityManagerFactory.createEntityManager();
    }

    public static Session getSession(){
        return getEm().unwrap(Session.class);
    }

    public static void closeEMFactory(){
        entityManagerFactory.close();
    }
}
