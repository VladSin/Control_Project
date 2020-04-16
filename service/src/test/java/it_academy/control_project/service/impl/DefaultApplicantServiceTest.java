package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IApplicantStorage;
import it_academy.control_project.data.Applicant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultApplicantServiceTest {

    @Mock
    IApplicantStorage dao;

    @InjectMocks
    DefaultApplicantService service;

    @Test
    void getNonExistentApplicant(){
        when(dao.getApplicant(0L)).thenReturn(null);
        Applicant applicant = service.getApplicant(0L);
        assertNull(applicant);
    }

    @Test
    void getExistingApplicant(){
        when(dao.getApplicant(1L)).thenReturn(new Applicant(1L, 1L, 1L, 10));
        Applicant applicantFromDb = service.getApplicant(1L);
        assertNotNull(applicantFromDb);
        assertEquals(applicantFromDb.getMark(), 10);
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getApplicant(2L)).thenReturn(null);
        Applicant applicantFromDb = service.getApplicant(2L);
        assertNull(applicantFromDb);
    }

    @Test
    void getApplicant(){
        when(dao.getApplicant(1L)).thenReturn(new Applicant(1L, 1L, 1L, 10));
        Applicant applicantFromDb = service.getApplicant(1L);
        assertNotNull(applicantFromDb);

        final Applicant applicant = service.getApplicant(1L);
        assertNotNull(applicant);
        assertEquals(applicantFromDb.getId(), applicant.getId());
        assertEquals(applicantFromDb.getUserId(), applicant.getUserId());
        assertEquals(applicantFromDb.getFacultyId(), applicant.getFacultyId());
        assertEquals(applicantFromDb.getMark(), applicant.getMark());
    }

    @Test
    void saveApplicant(){
        when(dao.getApplicant(1L)).thenReturn(new Applicant(1L, 1L, 1L, 10));
        Applicant applicantFromDb = service.getApplicant(1L);
        assertNotNull(applicantFromDb);

        when(dao.saveApplicant(applicantFromDb)).thenReturn(applicantFromDb);
        final Applicant applicant = service.saveApplicant(applicantFromDb);

        assertNotNull(applicant);
        assertEquals(applicantFromDb.getId(), applicant.getId());
        assertEquals(applicantFromDb.getUserId(), applicant.getUserId());
        assertEquals(applicantFromDb.getFacultyId(), applicant.getFacultyId());
        assertEquals(applicantFromDb.getMark(), applicant.getMark());
    }

    @Test
    void deleteApplicant(){
        when(dao.getApplicant(1L)).thenReturn(new Applicant(1L, 1L, 1L, 10));
        Applicant applicantFromDb = service.getApplicant(1L);
        assertNotNull(applicantFromDb);

        when(dao.deleteApplicant(1L)).thenReturn(true);
        final boolean delete = service.deleteApplicant(1L);
        assertTrue(delete);
    }

    @Test
    void updateApplicant(){
        when(dao.getApplicant(1L)).thenReturn(new Applicant(1L, 1L, 1L, 10));
        Applicant applicantFromDb = service.getApplicant(1L);
        assertNotNull(applicantFromDb);

        when(dao.updateApplicant(applicantFromDb)).thenReturn(true);
        final boolean update = service.updateApplicant(applicantFromDb);
        assertTrue(update);
    }
}