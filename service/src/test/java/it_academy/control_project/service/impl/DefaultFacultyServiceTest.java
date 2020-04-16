package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IFacultyStorage;
import it_academy.control_project.data.Faculty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultFacultyServiceTest {

    @Mock
    IFacultyStorage dao;

    @InjectMocks
    DefaultFacultyService service;

    @Test
    void getNonExistentUser(){
        when(dao.getFaculty(0)).thenReturn(null);
        Faculty faculty = service.getFaculty(0);
        assertNull(faculty);
    }

    @Test
    void getExistingFaculty(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);
        assertEquals(facultyFromDb.getFaculty(), "faculty");
        assertEquals(facultyFromDb.getMark(), 10);
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getFaculty(2)).thenReturn(null);
        Faculty facultyFromDb = service.getFaculty(2);
        assertNull(facultyFromDb);
    }

    @Test
    void getFaculty(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        final Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);

        final Faculty faculty = service.getFaculty(1);
        assertNotNull(faculty);
        assertEquals(facultyFromDb.getId(), faculty.getId());
        assertEquals(facultyFromDb.getFaculty(), faculty.getFaculty());
        assertEquals(facultyFromDb.getMark(), faculty.getMark());
    }

    @Test
    void saveFaculty(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        final Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);

        when(dao.saveFaculty(facultyFromDb)).thenReturn(facultyFromDb);
        final Faculty faculty = service.saveFaculty(facultyFromDb);

        assertNotNull(faculty);
        assertEquals(facultyFromDb.getId(), faculty.getId());
        assertEquals(facultyFromDb.getMark(), faculty.getMark());
        assertEquals(facultyFromDb.getFaculty(), faculty.getFaculty());
    }

    @Test
    void deleteFaculty(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        final Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);

        when(dao.deleteFaculty(1L)).thenReturn(true);
        final boolean delete = service.deleteFaculty(1L);
        assertTrue(delete);
    }

    @Test
    void updateFaculty(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        final Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);

        when(dao.updateFaculty(facultyFromDb)).thenReturn(true);
        final boolean update = service.updateFaculty(facultyFromDb);
        assertTrue(update);
    }
}