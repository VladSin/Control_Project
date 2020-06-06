package it.academy.vladsin.control.project.service.config;

import it.academy.vladsin.control.project.dao.config.DaoConfig;
import it.academy.vladsin.control.project.service.*;
import it.academy.vladsin.control.project.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Bean
    public ApplicantService applicantService(){
        return new DefaultApplicantService(daoConfig.applicantDao());
    }

    @Bean
    public AuthUserService authUserService(){
        return new DefaultAuthUserService(daoConfig.authUserDao());
    }

    @Bean
    public ExamService examService(){
        return new DefaultExamService(daoConfig.examDao());
    }

    @Bean
    public FacultyService facultyService(){
        return new DefaultFacultyService(daoConfig.facultyDao());
    }

    @Bean
    public SecurityService securityService(){
        return new DefaultSecurityService(daoConfig.authUserDao());
    }

    @Bean
    public StudentService studentService(){
        return new DefaultStudentService(daoConfig.studentDao());
    }

    @Bean
    public UniversityService universityService(){
        return new DefaultUniversityService(daoConfig.universityDao());
    }

    @Bean
    public UserService userService(){
        return new DefaultUserService(daoConfig.userDao());
    }
}
