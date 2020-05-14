package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.ApplicantDao;
import it_academy.control_project.data.Applicant;
import org.junit.jupiter.api.Test;

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
        final Applicant applicantToSave = new Applicant(null, 1L, 1L, 10);
        final Applicant savedApplicant = applicantDao.saveApplicant(applicantToSave);
        final Long id = savedApplicant.getId();

        final Applicant applicant = applicantDao.getApplicant(id);
        assertNotNull(applicant);
        assertEquals(applicantToSave.getUserId(), applicant.getUserId());
        assertEquals(applicantToSave.getFacultyId(), applicant.getFacultyId());
        assertEquals(applicantToSave.getMark(), applicant.getMark());
        assertEquals(id, applicant.getId());
    }
}