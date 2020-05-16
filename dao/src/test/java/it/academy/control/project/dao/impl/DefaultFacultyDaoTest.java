package it.academy.control.project.dao.impl;

import it.academy.control.project.dao.FacultyDao;
import it.academy.control.project.data.Faculty;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    void getList(){
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty(null, "Faculty1", 10));
        faculties.add(new Faculty(null, "Faculty2", 10));

        List<Faculty> facultyList = new ArrayList<>();
        for (Faculty f: faculties) {
            facultyList.add(facultyDao.saveFaculty(f));
        }

        assertNotNull(facultyList);
        for (int i = 0; i < facultyList.size(); i++) {
            assertEquals(facultyList.get(i).getFaculty(), faculties.get(i).getFaculty());
            assertEquals(facultyList.get(i).getMark(), faculties.get(i).getMark());
        }
    }
}