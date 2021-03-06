package it.academy.vladsin.control.project.dao.impl;

import it.academy.vladsin.control.project.dao.ApplicantDao;
import it.academy.vladsin.control.project.dao.config.DaoConfig;
import it.academy.vladsin.control.project.data.Applicant;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
@Commit
class DefaultApplicantDaoTest {

    @Autowired
    private ApplicantDao applicantDao;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    void saveApplicant() {
        final Applicant applicantToSave = new Applicant(null, 2L, 1L, 10);
        final Applicant savedApplicant = applicantDao.saveApplicant(applicantToSave);
        assertEquals(applicantToSave.getUserId(), savedApplicant.getUserId());
        assertEquals(applicantToSave.getFacultyId(), savedApplicant.getFacultyId());
        assertEquals(applicantToSave.getMark(), savedApplicant.getMark());
        assertNotNull(savedApplicant.getId());
    }

    @Test
    void deleteApplicant() {
        final Applicant applicantToSave = new Applicant(null, 3L, 1L, 10);
        final Applicant savedApplicant = applicantDao.saveApplicant(applicantToSave);
        final Long id = savedApplicant.getId();
        assertNotNull(savedApplicant);
        assertNotNull(id);

        final boolean deleted = applicantDao.deleteApplicant(id);
        assertTrue(deleted);
        sessionFactory.getCurrentSession().clear();

        final Applicant afterDelete = applicantDao.getApplicant(id);
        assertNull(afterDelete);
    }

    @Test
    void updateApplicant() {
        final Applicant applicantToSave = new Applicant(null, 4L, 1L, 10);
        final Applicant savedApplicant = applicantDao.saveApplicant(applicantToSave);
        final Long id = savedApplicant.getId();
        assertNotNull(savedApplicant);
        assertNotNull(id);
        sessionFactory.getCurrentSession().clear();

        final Applicant toUpdate = new Applicant(id, 5L, 2L, savedApplicant.getMark());
        final boolean updated = applicantDao.updateApplicant(toUpdate);
        assertTrue(updated);

        final Applicant afterUpdate = applicantDao.getApplicant(id);
        assertEquals(toUpdate.getUserId(), afterUpdate.getUserId());
        assertEquals(toUpdate.getFacultyId(), afterUpdate.getFacultyId());
        assertEquals(toUpdate.getMark(), afterUpdate.getMark());
    }


    @Test
    void getApplicant() {
        final Applicant applicant = new Applicant(null, 1L, 1L, 10);
        Applicant applicantToSave = applicantDao.saveApplicant(applicant);
        assertNotNull(applicantToSave);

        Applicant applicantToGet = applicantDao.getApplicant(1L);
        assertNotNull(applicantToGet);
    }

    @Test
    void getList(){
        List<Applicant> applicants = new ArrayList<>();
        applicants.add(new Applicant(null, 6L, 1L, 10));
        applicants.add(new Applicant(null, 7L, 2L, 10));

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
        applicants.add(new Applicant(null, 8L, 1L, 10));
        applicants.add(new Applicant(null, 9L, 2L, 10));

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