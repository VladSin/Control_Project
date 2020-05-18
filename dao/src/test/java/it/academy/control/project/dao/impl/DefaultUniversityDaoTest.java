package it.academy.control.project.dao.impl;

import it.academy.control.project.dao.StudentDao;
import it.academy.control.project.dao.UniversityDao;
import it.academy.control.project.dao.util.HibernateUtil;
import it.academy.control.project.data.Student;
import it.academy.control.project.data.University;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultUniversityDaoTest {

    UniversityDao universityDao = DefaultUniversityDao.getInstance();

    @Test
    void saveUniversity() {
        final University universityToSave = new University(null, "university", null);
        final University savedUniversity = universityDao.saveUniversity(universityToSave);
        assertEquals(universityToSave.getUniversity(), savedUniversity.getUniversity());
        assertEquals(universityToSave.getStudents(), savedUniversity.getStudents());
        assertNotNull(savedUniversity.getId());
    }

    @Test
    void deleteUniversity() {
        final University universityToSave = new University(null, "university", null);
        final University savedUniversity = universityDao.saveUniversity(universityToSave);
        final Long id = savedUniversity.getId();
        final University university = universityDao.getUniversity(id);
        assertNotNull(university);

        final boolean deleted = universityDao.deleteUniversity(id);
        assertTrue(deleted);

        final University afterDelete = universityDao.getUniversity(id);
        assertNull(afterDelete);
    }

    @Test
    void updateUniversity() {
        final University universityToSave = new University(null, "university", null);
        final University savedUniversity = universityDao.saveUniversity(universityToSave);
        final Long id = savedUniversity.getId();

        final University toUpdate = new University(id, "university", null);
        final boolean updated = universityDao.updateUniversity(toUpdate);
        assertTrue(updated);

        final University afterUpdate = universityDao.getUniversity(id);
        assertEquals(toUpdate.getId(), afterUpdate.getId());
        assertEquals(toUpdate.getStudents(), afterUpdate.getStudents());
        assertEquals(toUpdate.getUniversity(), afterUpdate.getUniversity());
    }

    @Test
    void getList(){
        List<University> universities = new ArrayList<>();
        universities.add(new University(null, "university1", null));
        universities.add(new University(null, "university2", null));

        List<University> universityList = new ArrayList<>();
        for (University u: universities) {
            universityList.add(universityDao.saveUniversity(u));
        }

        assertNotNull(universityList);
        for (int i = 0; i < universityList.size(); i++) {
            assertEquals(universityList.get(i).getUniversity(), universities.get(i).getUniversity());
            assertEquals(universityList.get(i).getStudents(), universities.get(i).getStudents());
        }
        universities = universityDao.getUniversities();
        assertNotNull(universities);
    }

    @AfterClass
    public void cleanUp() {
        HibernateUtil.closeEMFactory();
    }
}
