package it.academy.vladsin.control.project.dao.config;

import it.academy.vladsin.control.project.dao.*;
import it.academy.vladsin.control.project.dao.impl.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
public class DaoConfig {

    private final SessionFactory sessionFactory;

    public DaoConfig(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Bean
    public ApplicantDao applicantDao(){
        return new DefaultApplicantDao(sessionFactory);
    }

    @Bean
    public AuthUserDao authUserDao(){
        return new DefaultAuthUserDao(sessionFactory);
    }

    @Bean
    public ExamDao examDao(){
        return new DefaultExamDao(sessionFactory);
    }

    @Bean
    public FacultyDao facultyDao(){
        return new DefaultFacultyDao(sessionFactory);
    }

    @Bean
    public StudentDao studentDao(){
        return new DefaultStudentDao(sessionFactory);
    }

    @Bean
    public UniversityDao universityDao(){
        return new DefaultUniversityDao(sessionFactory);
    }

    @Bean
    public UserDao userDao(){
        return new DefaultUserDao(sessionFactory);
    }
}
