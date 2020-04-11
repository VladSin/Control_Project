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
    public List<Faculty> getFaculty() {
        return DefaultFacultyStorage.getInstance().getFaculty();
    }

    @Override
    public Long saveFaculty(Faculty faculty) {
        return DefaultFacultyStorage.getInstance().save(faculty);
    }
}
