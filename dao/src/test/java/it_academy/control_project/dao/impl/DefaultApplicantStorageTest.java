package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.IApplicantStorage;
import it_academy.control_project.data.Applicant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultApplicantStorageTest {

    IApplicantStorage applicantStorage = DefaultApplicantStorage.getInstance();

    @Test
    void saveApplicant() {
        final Applicant applicantToSave = new Applicant(null, 1L, 1L, 10);
        final Applicant savedApplicant = applicantStorage.saveApplicant(applicantToSave);
        assertEquals(applicantToSave.getUserId(), savedApplicant.getUserId());
        assertEquals(applicantToSave.getFacultyId(), savedApplicant.getFacultyId());
        assertEquals(applicantToSave.getMark(), savedApplicant.getMark());
        assertNotNull(savedApplicant.getId());
    }

    @Test
    void deleteApplicant() {
        final Applicant applicantToSave = new Applicant(null, 1L, 1L, 10);
        final Applicant savedApplicant = applicantStorage.saveApplicant(applicantToSave);
        final Long id = savedApplicant.getId();
        final Applicant applicant = applicantStorage.getApplicant(id);
        assertNotNull(applicant);

        final boolean deleted = applicantStorage.deleteApplicant(id);
        assertTrue(deleted);

        final Applicant afterDelete = applicantStorage.getApplicant(id);
        assertNull(afterDelete);
    }

    @Test
    void updateApplicant() {
        final Applicant applicantToSave = new Applicant(null, 1L, 1L, 10);
        final Applicant savedApplicant = applicantStorage.saveApplicant(applicantToSave);
        final Long id = savedApplicant.getId();

        final Applicant toUpdate = new Applicant(id, 2L, 2L, savedApplicant.getMark());
        final boolean updated = applicantStorage.updateApplicant(toUpdate);
        assertTrue(updated);

        final Applicant afterUpdate = applicantStorage.getApplicant(id);
        assertEquals(toUpdate.getUserId(), afterUpdate.getUserId());
        assertEquals(toUpdate.getFacultyId(), afterUpdate.getFacultyId());
        assertEquals(toUpdate.getMark(), afterUpdate.getMark());
    }

    @Test
    void getApplicant() {
        final Applicant applicantToSave = new Applicant(null, 1L, 1L, 10);
        final Applicant savedApplicant = applicantStorage.saveApplicant(applicantToSave);
        final Long id = savedApplicant.getId();

        final Applicant applicant = applicantStorage.getApplicant(id);
        assertNotNull(applicant);
        assertEquals(applicantToSave.getUserId(), applicant.getUserId());
        assertEquals(applicantToSave.getFacultyId(), applicant.getFacultyId());
        assertEquals(applicantToSave.getMark(), applicant.getMark());
        assertEquals(id, applicant.getId());
    }
}