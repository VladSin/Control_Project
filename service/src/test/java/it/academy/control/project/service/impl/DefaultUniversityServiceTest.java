package it.academy.control.project.service.impl;

import it.academy.control.project.dao.UniversityDao;
import it.academy.control.project.data.Student;
import it.academy.control.project.data.University;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultUniversityServiceTest {

    @Mock
    UniversityDao dao;

    @InjectMocks
    DefaultUniversityService service;

    @Test
    void getNonExistentApplicant(){
        when(dao.getUniversity(0L)).thenReturn(null);
        University university = service.getUniversity(0L);
        assertNull(university);
    }

    @Test
    void getExistingApplicant(){
        List<Student> students = new ArrayList<>();
        when(dao.getUniversity(1L)).thenReturn(new University(1L, "university", students));
        University universityFromDb = service.getUniversity(1L);
        assertNotNull(universityFromDb);
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getUniversity(2L)).thenReturn(null);
        University universityFromDb = service.getUniversity(2L);
        assertNull(universityFromDb);
    }

    @Test
    void getApplicant(){
        List<Student> students = new ArrayList<>();
        when(dao.getUniversity(1L)).thenReturn(new University(1L, "university", students));
        University universityFromDb = service.getUniversity(1L);
        assertNotNull(universityFromDb);

        final University university = service.getUniversity(1L);
        assertNotNull(university);
        assertEquals(universityFromDb.getId(), university.getId());
        assertEquals(universityFromDb.getUniversity(), university.getUniversity());
        assertEquals(universityFromDb.getStudents(), university.getStudents());
    }

    @Test
    void saveApplicant(){
        List<Student> students = new ArrayList<>();
        when(dao.getUniversity(1L)).thenReturn(new University(1L, "university", students));
        University universityFromDb = service.getUniversity(1L);
        assertNotNull(universityFromDb);

        when(dao.saveUniversity(universityFromDb)).thenReturn(universityFromDb);
        final University university = service.saveUniversity(universityFromDb);

        assertNotNull(university);
        assertEquals(universityFromDb.getId(), university.getId());
        assertEquals(universityFromDb.getUniversity(), university.getUniversity());
        assertEquals(universityFromDb.getStudents(), university.getStudents());
    }

    @Test
    void deleteApplicant(){
        List<Student> students = new ArrayList<>();
        when(dao.getUniversity(1L)).thenReturn(new University(1L, "university", students));
        University universityFromDb = service.getUniversity(1L);
        assertNotNull(universityFromDb);

        when(dao.deleteUniversity(1L)).thenReturn(true);
        final boolean delete = service.deleteUniversity(1L);
        assertTrue(delete);
    }

    @Test
    void updateApplicant(){
        List<Student> students = new ArrayList<>();
        when(dao.getUniversity(1L)).thenReturn(new University(1L, "university", students));
        University universityFromDb = service.getUniversity(1L);
        assertNotNull(universityFromDb);

        when(dao.updateUniversity(universityFromDb)).thenReturn(true);
        final boolean update = service.updateUniversity(universityFromDb);
        assertTrue(update);
    }

    @Test
    void getList() {
        List<University> universities = new ArrayList<>();
        universities.add(new University(1L, "university1", null));
        universities.add(new University(2L, "university2", null));
        when(dao.getUniversities()).thenReturn(universities);

        List<University> universitiesDao = dao.getUniversities();
        assertNotNull(universitiesDao);
        for (int i = 0; i < universitiesDao.size(); i++) {
            assertEquals(universitiesDao.get(i).getId(), universities.get(i).getId());
            assertEquals(universitiesDao.get(i).getUniversity(), universities.get(i).getUniversity());
            assertEquals(universitiesDao.get(i).getStudents(), universities.get(i).getStudents());
        }
    }
}
