package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IFacultyStorage;
import it_academy.control_project.dao.IUserStorage;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class DefaultFacultyServiceTest {

    @Mock
    IFacultyStorage dao;

    @InjectMocks
    DefaultFacultyService service;

}