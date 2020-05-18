package it.academy.control.project.service.impl;

import it.academy.control.project.dao.ApplicantDao;
import it.academy.control.project.data.Applicant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultApplicantServiceTest {

    @Mock
    ApplicantDao dao;

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

    @Test
    void getList() {
        List<Applicant> applicants = new ArrayList<>();
        applicants.add(new Applicant(1L, 1L, 1L, 10));
        applicants.add(new Applicant(2L, 2L, 2L, 10));
        when(dao.getApplicants()).thenReturn(applicants);

        List<Applicant> applicantDao = dao.getApplicants();
        assertNotNull(applicantDao);
        for (int i = 0; i < applicantDao.size(); i++) {
            assertEquals(applicantDao.get(i).getId(), applicants.get(i).getId());
            assertEquals(applicantDao.get(i).getUserId(), applicants.get(i).getUserId());
            assertEquals(applicantDao.get(i).getFacultyId(), applicants.get(i).getFacultyId());
            assertEquals(applicantDao.get(i).getMark(), applicants.get(i).getMark());
        }
    }

    @Test
    void getListNumber() {
        List<Applicant> applicants = new ArrayList<>();
        applicants.add(new Applicant(1L, 1L, 1L, 10));
        applicants.add(new Applicant(2L, 2L, 2L, 10));
        when(dao.getApplicants(2)).thenReturn(applicants);

        List<Applicant> applicantDao = dao.getApplicants(2);
        assertNotNull(applicantDao);
        for (int i = 0; i < applicantDao.size(); i++) {
            assertEquals(applicantDao.get(i).getId(), applicants.get(i).getId());
            assertEquals(applicantDao.get(i).getUserId(), applicants.get(i).getUserId());
            assertEquals(applicantDao.get(i).getFacultyId(), applicants.get(i).getFacultyId());
            assertEquals(applicantDao.get(i).getMark(), applicants.get(i).getMark());
        }
    }
}