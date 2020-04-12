package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IApplicantStorage;
import it_academy.control_project.data.Applicant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    void getNonExistentUser(){
        when(dao.getApplicant(0)).thenReturn(null);
        Applicant applicant = service.getApplicant(0);
        assertNull(applicant);
    }

    @Test
    void getExistingUser(){
        when(dao.getApplicant(1)).thenReturn(new Applicant(1L, 1L, 1L, 10));
        Applicant applicantFromDb = service.getApplicant(1);
        assertNotNull(applicantFromDb);
        assertEquals(applicantFromDb.getMark(), 10);
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getApplicant(1)).thenReturn(new Applicant(1L, 1L, 1L, 10));
        Applicant applicantFromDb = service.getApplicant(2);
        assertNull(applicantFromDb);
    }

    @Test
    void deleteUser(){
        when(dao.getApplicant(1)).thenReturn(new Applicant(1L, 1L, 1L, 10));
        Applicant applicantFromDb = service.getApplicant(1);
        assertNotNull(applicantFromDb);

        final boolean deleted = service.deleteApplicant(1);
        assertTrue(deleted);

        final Applicant afterDeleted = service.getApplicant(1);
        assertNull(afterDeleted);
    }

    @Test
    void getUser(){
        when(dao.getApplicant(1)).thenReturn(new Applicant(1L, 1L, 1L, 10));
        Applicant applicantFromDb = service.getApplicant(1);
        assertNotNull(applicantFromDb);

        final Applicant applicant = service.getApplicant(1);
        assertNotNull(applicant);
        assertEquals(applicantFromDb.getId(), applicant.getId());
        assertEquals(applicantFromDb.getUserId(), applicant.getUserId());
        assertEquals(applicantFromDb.getFacultyId(), applicant.getFacultyId());
        assertEquals(applicantFromDb.getMark(), applicant.getMark());
    }
}