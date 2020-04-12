package it_academy.control_project.dao.impl;

import it_academy.control_project.dao.IFacultyStorage;
import it_academy.control_project.data.Faculty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultFacultyStorageTest {

    IFacultyStorage facultyStorage = DefaultFacultyStorage.getInstance();

    @Test
    void saveFaculty() {
        final Faculty facultyToSave = new Faculty(null,"faculty", 10);
        final Faculty savedFaculty = facultyStorage.saveFaculty(facultyToSave);

        assertEquals(facultyToSave.getId(), savedFaculty.getId());
        assertEquals(facultyToSave.getFaculty(), savedFaculty.getFaculty());
        assertEquals(facultyToSave.getMark(), savedFaculty.getMark());
    }

    @Test
    void deleteFaculty() {
        final Faculty facultyToSave = new Faculty(null,"faculty", 10);
        final Faculty savedFaculty = facultyStorage.saveFaculty(facultyToSave);
        final Long id = savedFaculty.getId();

        final Faculty faculty = facultyStorage.getFaculty(id);
        assertNull(faculty);

        final boolean deleted = facultyStorage.deleteFaculty(id);
        assertTrue(deleted);

        final Faculty afterDeleted = facultyStorage.getFaculty(id);
        assertNull(afterDeleted);
    }

    @Test
    void updateFaculty() {
        final Faculty facultyToSave = new Faculty(null,"faculty", 10);
        final Faculty savedFaculty = facultyStorage.saveFaculty(facultyToSave);
        final Long id = savedFaculty.getId();

        final Faculty toUpdate = new Faculty(null,"faculty", 10);
        final boolean update = facultyStorage.updateFaculty(toUpdate);
        assertTrue(update);

        final Faculty afterUpdate = facultyStorage.getFaculty(id);
        assertEquals(toUpdate.getId(), afterUpdate.getId());
        assertEquals(toUpdate.getFaculty(), afterUpdate.getFaculty());
        assertEquals(toUpdate.getMark(), afterUpdate.getMark());
    }

    @Test
    void getFaculty() {
        final Faculty facultyToSave = new Faculty(null,"faculty", 10);
        final Faculty savedFaculty = facultyStorage.saveFaculty(facultyToSave);
        final Long id = savedFaculty.getId();

        final Faculty faculty = facultyStorage.getFaculty(id);
        assertNotNull(faculty);
        assertEquals(facultyToSave.getId(), faculty.getId());
        assertEquals(facultyToSave.getFaculty(), faculty.getFaculty());
        assertEquals(facultyToSave.getMark(), faculty.getMark());
        assertEquals(id, faculty.getId());
    }
}