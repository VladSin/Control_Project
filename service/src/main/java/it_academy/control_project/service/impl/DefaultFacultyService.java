package it_academy.control_project.service.impl;

import it_academy.control_project.dao.impl.DefaultFacultyStorage;
import it_academy.control_project.data.Faculty;
import it_academy.control_project.service.IFacultyService;

import java.util.List;

public class DefaultFacultyService implements IFacultyService {

    private static class SingletonHolder {
        static final IFacultyService HOLDER_INSTANCE = new DefaultFacultyService();
    }
    public static IFacultyService getInstance() {
        return DefaultFacultyService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Faculty saveFaculty(Faculty faculty) {
        return DefaultFacultyStorage.getInstance().saveFaculty(faculty);
    }

    @Override
    public List<Faculty> saveFaculty(List<Faculty> faculties) {
        return DefaultFacultyStorage.getInstance().saveFaculty(faculties);
    }

    @Override
    public boolean deleteFaculty(long id) {
        return DefaultFacultyStorage.getInstance().deleteFaculty(id);
    }

    @Override
    public boolean updateFaculty(Faculty faculty) {
        return DefaultFacultyStorage.getInstance().updateFaculty(faculty);
    }

    @Override
    public Faculty getFaculty(long id) {
        return DefaultFacultyStorage.getInstance().getFaculty(id);
    }

    @Override
    public List<Faculty> getFaculty() {
        return DefaultFacultyStorage.getInstance().getFaculty();
    }
}
