package it.academy.control.project.dao.impl;

import it.academy.control.project.dao.ApplicantDao;
import it.academy.control.project.dao.UserDao;
import it.academy.control.project.dao.entity.ApplicantEntity;
import it.academy.control.project.dao.entity.UserEntity;
import it.academy.control.project.dao.util.HibernateUtil;
import it.academy.control.project.data.Applicant;
import it.academy.control.project.data.AuthorizationUser;
import it.academy.control.project.data.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultApplicantDaoTest {

    ApplicantDao applicantDao = DefaultApplicantDao.getInstance();

    @Test
    void saveApplicant() {
        final Applicant applicantToSave = new Applicant(null, 1L, 1L, 10);
        final Applicant savedApplicant = applicantDao.saveApplicant(applicantToSave);
        assertEquals(applicantToSave.getUserId(), savedApplicant.getUserId());
        assertEquals(applicantToSave.getFacultyId(), savedApplicant.getFacultyId());
        assertEquals(applicantToSave.getMark(), savedApplicant.getMark());
        assertNotNull(savedApplicant.getId());
    }

    @Test
    void deleteApplicant() {
        final Applicant applicantToSave = new Applicant(null, 1L, 1L, 10);
        final Applicant savedApplicant = applicantDao.saveApplicant(applicantToSave);
        final Long id = savedApplicant.getId();
        final Applicant applicant = applicantDao.getApplicant(id);
        assertNotNull(applicant);

        final boolean deleted = applicantDao.deleteApplicant(id);
        assertTrue(deleted);

        final Applicant afterDelete = applicantDao.getApplicant(id);
        assertNull(afterDelete);
    }

    @Test
    void updateApplicant() {
        final Applicant applicantToSave = new Applicant(null, 1L, 1L, 10);
        final Applicant savedApplicant = applicantDao.saveApplicant(applicantToSave);
        final Long id = savedApplicant.getId();

        final Applicant toUpdate = new Applicant(id, 2L, 2L, savedApplicant.getMark());
        final boolean updated = applicantDao.updateApplicant(toUpdate);
        assertTrue(updated);

        final Applicant afterUpdate = applicantDao.getApplicant(id);
        assertEquals(toUpdate.getUserId(), afterUpdate.getUserId());
        assertEquals(toUpdate.getFacultyId(), afterUpdate.getFacultyId());
        assertEquals(toUpdate.getMark(), afterUpdate.getMark());
    }


    @Test
    void getApplicant() {
        final User user = new User(null, "name", "surname", "phone","email");
        User userToSave = DefaultUserDao.getInstance().saveUser(user);
        assertNotNull(userToSave);

        final Applicant applicant = new Applicant(null, userToSave.getId(), 1L, 10);
        Applicant applicantToSave = applicantDao.saveApplicant(applicant);
        assertNotNull(applicantToSave);

        assertEquals(userToSave.getId(), applicantToSave.getUserId());
    }
//    @Test
//    void getApplicant() {
//        final ApplicantEntity applicantEntity = new ApplicantEntity(null, 1L, 1L, 10, null);
//        final UserEntity userEntity = new UserEntity(null, "name", "surname", "phone","email", null);
//
//        userEntity.setApplicantEntity(applicantEntity);
//        applicantEntity.setUserEntity(userEntity);
//
//        final Session session = HibernateUtil.getSession();
//        session.beginTransaction();
//        session.save(userEntity);
//        session.save(applicantEntity);
//        session.getTransaction().commit();
//
//        final User user = DefaultUserDao.getInstance().getUser(1L);
//        final Applicant applicant = applicantDao.getApplicant(user.getId());
//        assertNotNull(applicant);
//    }

    @Test
    void getList(){
        List<Applicant> applicants = new ArrayList<>();
        applicants.add(new Applicant(null, 1L, 1L, 10));
        applicants.add(new Applicant(null, 2L, 2L, 10));

        List<Applicant> applicantList = new ArrayList<>();
        for (Applicant a: applicants) {
            applicantList.add(applicantDao.saveApplicant(a));
        }

        assertNotNull(applicantList);
        for (int i = 0; i < applicantList.size(); i++) {
            assertEquals(applicantList.get(i).getUserId(), applicants.get(i).getUserId());
            assertEquals(applicantList.get(i).getFacultyId(), applicants.get(i).getFacultyId());
            assertEquals(applicantList.get(i).getMark(), applicants.get(i).getMark());
        }
        applicants = applicantDao.getApplicants();
        assertNotNull(applicants);
    }

    @Test
    void getListNumber(){
        List<Applicant> applicants = new ArrayList<>();
        applicants.add(new Applicant(null, 1L, 1L, 10));
        applicants.add(new Applicant(null, 2L, 2L, 10));

        List<Applicant> applicantList = new ArrayList<>();
        for (Applicant a: applicants) {
            applicantList.add(applicantDao.saveApplicant(a));
        }

        assertNotNull(applicantList);
        for (int i = 0; i < applicantList.size(); i++) {
            assertEquals(applicantList.get(i).getUserId(), applicants.get(i).getUserId());
            assertEquals(applicantList.get(i).getFacultyId(), applicants.get(i).getFacultyId());
            assertEquals(applicantList.get(i).getMark(), applicants.get(i).getMark());
        }

        applicants = applicantDao.getApplicants(1);
        assertNotNull(applicants);
    }
}