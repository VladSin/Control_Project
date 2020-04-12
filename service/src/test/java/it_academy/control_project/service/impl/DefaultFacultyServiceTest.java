package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IFacultyStorage;
import it_academy.control_project.data.Faculty;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
    void getExistingUser(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);
        assertEquals(facultyFromDb.getFaculty(), "faculty");
        assertEquals(facultyFromDb.getMark(), 10);
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        Faculty facultyFromDb = service.getFaculty(2);
        assertNull(facultyFromDb);
    }

    @Test
    void deleteUser(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        final Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);

        final boolean deleted = service.deleteFaculty(1);
        assertTrue(deleted);

        final Faculty afterDeleted = service.getFaculty(1);
        assertNull(afterDeleted);
    }

    @Test
    void getUser(){
        when(dao.getFaculty(1)).thenReturn(new Faculty(1L, "faculty", 10));
        final Faculty facultyFromDb = service.getFaculty(1);
        assertNotNull(facultyFromDb);

        final Faculty faculty = service.getFaculty(1);
        assertNotNull(faculty);
        assertEquals(facultyFromDb.getId(), faculty.getId());
        assertEquals(facultyFromDb.getFaculty(), faculty.getFaculty());
        assertEquals(facultyFromDb.getMark(), faculty.getMark());
    }
}