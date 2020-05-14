package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.FacultyDao;
import it_academy.control_project.data.Faculty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultFacultyDaoTest {

    FacultyDao facultyDao = DefaultFacultyDao.getInstance();

    @Test
    void saveFaculty() {
        final Faculty facultyToSave = new Faculty(null,"faculty", 10);
        final Faculty savedFaculty = facultyDao.saveFaculty(facultyToSave);

        assertEquals(facultyToSave.getFaculty(), savedFaculty.getFaculty());
        assertEquals(facultyToSave.getMark(), savedFaculty.getMark());
    }

    @Test
    void deleteFaculty() {
        final Faculty facultyToSave = new Faculty(null,"faculty", 10);
        final Faculty savedFaculty = facultyDao.saveFaculty(facultyToSave);
        final Long id = savedFaculty.getId();

        final Faculty faculty = facultyDao.getFaculty(id);
        assertNotNull(faculty);

        final boolean deleted = facultyDao.deleteFaculty(id);
        assertTrue(deleted);

        final Faculty afterDeleted = facultyDao.getFaculty(id);
        assertNull(afterDeleted);
    }

    @Test
    void updateFaculty() {
        final Faculty facultyToSave = new Faculty(null,"faculty", 10);
        final Faculty savedFaculty = facultyDao.saveFaculty(facultyToSave);
        final Long id = savedFaculty.getId();

        final Faculty toUpdate = new Faculty(id,"faculty", 10);
        final boolean update = facultyDao.updateFaculty(toUpdate);
        assertTrue(update);

        final Faculty afterUpdate = facultyDao.getFaculty(id);

        assertEquals(toUpdate.getFaculty(), afterUpdate.getFaculty());
        assertEquals(toUpdate.getMark(), afterUpdate.getMark());
    }

    @Test
    void getFaculty() {
        final Faculty facultyToSave = new Faculty(null,"faculty", 10);
        final Faculty savedFaculty = facultyDao.saveFaculty(facultyToSave);
        final Long id = savedFaculty.getId();

        final Faculty faculty = facultyDao.getFaculty(id);
        assertNotNull(faculty);

        assertEquals(facultyToSave.getFaculty(), faculty.getFaculty());
        assertEquals(facultyToSave.getMark(), faculty.getMark());
        assertEquals(id, faculty.getId());
    }
}