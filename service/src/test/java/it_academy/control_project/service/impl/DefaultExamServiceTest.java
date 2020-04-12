package it_academy.control_project.service.impl;

import it_academy.control_project.dao.IExamStorage;
import it_academy.control_project.dao.IUserStorage;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class DefaultExamServiceTest {

    @Mock
    IExamStorage dao;

    @InjectMocks
    DefaultExamService service;

}